package book.web.loader;


import book.web.config.GatewayRoutersConfiguration;
import book.web.config.RouterDataType;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.google.common.collect.Lists;
import book.web.cty.constant.CacheConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import book.web.cty.util.BaseMap;
import book.web.cty.redis.util.RedisUtil;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 动态路由加载器
 *
 * @author cty
 * @date 2022/6/18
 */
@Slf4j
@Component
@DependsOn({"gatewayRoutersConfiguration"})
public class DynamicRouteLoader implements ApplicationEventPublisherAware {


    private ApplicationEventPublisher publisher;

    @Autowired
    private DynamicRouteService dynamicRouteService;

    private ConfigService configService;

    @Autowired
    private RedisUtil redisUtil;


    public DynamicRouteLoader(InMemoryRouteDefinitionRepository repository, DynamicRouteService dynamicRouteService, RedisUtil redisUtil) {

        this.dynamicRouteService = dynamicRouteService;
        this.redisUtil = redisUtil;
    }

    @PostConstruct
    public void init() {
        init(null);
    }

    public void init(BaseMap baseMap) {
        String dataType = GatewayRoutersConfiguration.DATA_TYPE;
        log.info("初始化路由，dataType：" + dataType);
        // 从nacos加载路由
        if (RouterDataType.nacos.toString().endsWith(dataType)) {
            loadRoutesByNacos();
        }
        //从数据库加载路由
        if (RouterDataType.database.toString().endsWith(dataType)) {
            loadRoutesByRedis(baseMap);
        }
    }

    /**
     * 刷新路由
     *
     * @return
     */
    public Mono<Void> refresh(BaseMap baseMap) {
        String dataType = GatewayRoutersConfiguration.DATA_TYPE;
        if (!RouterDataType.yml.toString().endsWith(dataType)) {
            this.init(baseMap);
        }
        return Mono.empty();
    }


    /**
     * 从nacos中读取路由配置
     *
     * @return
     */
    private void loadRoutesByNacos() {
        List<RouteDefinition> routes = Lists.newArrayList();
        configService = createConfigService();
        if (configService == null) {
            log.warn("initConfigService fail");
        }
        try {
            String configInfo = configService.getConfig(GatewayRoutersConfiguration.DATA_ID, GatewayRoutersConfiguration.ROUTE_GROUP, GatewayRoutersConfiguration.DEFAULT_TIMEOUT);
            if (StringUtils.isNotBlank(configInfo)) {
                log.info("获取网关当前配置:\r\n{}", configInfo);
                routes = JSON.parseArray(configInfo, RouteDefinition.class);
            }
        } catch (NacosException e) {
            log.error("初始化网关路由时发生错误", e);
            e.printStackTrace();
        }
        for (RouteDefinition definition : routes) {
            log.info("update route : {}", definition.toString());
            dynamicRouteService.add(definition);
        }
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        dynamicRouteByNacosListener(GatewayRoutersConfiguration.DATA_ID, GatewayRoutersConfiguration.ROUTE_GROUP);
    }


    /**
     * 从redis中读取路由配置
     *
     * @return
     */
    private void loadRoutesByRedis(BaseMap baseMap) {
        List<MyRouteDefinition> routes = Lists.newArrayList();
        configService = createConfigService();
        if (configService == null) {
            log.warn("initConfigService fail");
        }
        Object configInfo = redisUtil.get(CacheConstant.GATEWAY_ROUTES);
        if (ObjectUtil.isNotEmpty(configInfo)) {
            log.info("获取网关当前配置:\r\n{}", configInfo);
            JSONArray array = JSON.parseArray(configInfo.toString());
            try {
                routes = getRoutesByJson(array);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        for (MyRouteDefinition definition : routes) {
            log.info("update route : {}", definition.toString());
            Integer status = definition.getStatus();
            if (status.equals(0)) {
                dynamicRouteService.delete(definition.getId());
            } else {
                dynamicRouteService.add(definition);
            }
        }
        if (ObjectUtils.isNotEmpty(baseMap)) {
            String routerId = baseMap.get("routerId");
            if (ObjectUtils.isNotEmpty(routerId)) {
                dynamicRouteService.delete(routerId);
            }
        }
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * redis中的信息需要处理下 转成RouteDefinition对象
     * - id: login
     * uri: lb://cloud-jeecg-system
     * predicates:
     * - Path=/jeecg-boot/sys/**,
     *
     * @param array
     * @return
     */

    public static List<MyRouteDefinition> getRoutesByJson(JSONArray array) throws URISyntaxException {
        List<MyRouteDefinition> ls = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            MyRouteDefinition route = new MyRouteDefinition();
            route.setId(obj.getString("routerId"));
            route.setStatus(obj.getInteger("status"));
            Object uri = obj.get("uri");
            if (uri == null) {
                route.setUri(new URI("lb://" + obj.getString("name")));
            } else {
                route.setUri(new URI(obj.getString("uri")));
            }
            Object predicates = obj.get("predicates");
            if (predicates != null) {
                JSONArray list = JSON.parseArray(predicates.toString());
                List<PredicateDefinition> predicateDefinitionList = new ArrayList<>();
                for (Object map : list) {
                    JSONObject json = (JSONObject) map;
                    PredicateDefinition predicateDefinition = new PredicateDefinition();
                    predicateDefinition.setName(json.getString("name"));
                    JSONArray jsonArray = json.getJSONArray("args");
                    for (int j = 0; j < jsonArray.size(); j++) {
                        predicateDefinition.addArg("_genkey" + j, jsonArray.get(j).toString());
                    }
                    predicateDefinitionList.add(predicateDefinition);
                }
                route.setPredicates(predicateDefinitionList);
            }

            Object filters = obj.get("filters");
            if (filters != null) {
                JSONArray list = JSON.parseArray(filters.toString());
                List<FilterDefinition> filterDefinitionList = new ArrayList<>();
                if (ObjectUtil.isNotEmpty(list)) {
                    for (Object map : list) {
                        JSONObject json = (JSONObject) map;
                        JSONArray jsonArray = json.getJSONArray("args");
                        String name = json.getString("name");
                        FilterDefinition filterDefinition = new FilterDefinition();
                        for (Object o : jsonArray) {
                            JSONObject params = (JSONObject) o;
                            filterDefinition.addArg(params.getString("key"), params.get("value").toString());
                        }
                        filterDefinition.setName(name);
                        filterDefinitionList.add(filterDefinition);
                    }
                    route.setFilters(filterDefinitionList);
                }
            }
            ls.add(route);
        }
        return ls;
    }

    /**
     * 监听Nacos下发的动态路由配置
     *
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener(String dataId, String group) {
        try {
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("进行网关更新:\n\r{}", configInfo);
                    List<MyRouteDefinition> definitionList = JSON.parseArray(configInfo, MyRouteDefinition.class);
                    for (MyRouteDefinition definition : definitionList) {
                        log.info("update route : {}", definition.toString());
                        dynamicRouteService.update(definition);
                    }
                }

                @Override
                public Executor getExecutor() {
                    log.info("getExecutor\n\r");
                    return null;
                }
            });
        } catch (Exception e) {
            log.error("从nacos接收动态路由配置出错!!!", e);
        }
    }

    /**
     * 创建ConfigService
     *
     * @return
     */
    private ConfigService createConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", GatewayRoutersConfiguration.SERVER_ADDR);
            properties.setProperty("namespace", GatewayRoutersConfiguration.NAMESPACE);
            properties.setProperty("username", GatewayRoutersConfiguration.USERNAME);
            properties.setProperty("password", GatewayRoutersConfiguration.PASSWORD);
            return configService = NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            log.error("创建ConfigService异常", e);
            return null;
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
