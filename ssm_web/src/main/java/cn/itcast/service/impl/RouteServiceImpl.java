package cn.itcast.service.impl;

import cn.itcast.dao.IRouteDao;
import cn.itcast.domain.Route;
import cn.itcast.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:16:13
 */
@Service("routeService")
public class RouteServiceImpl implements IRouteService {

    @Autowired
    private IRouteDao routeDao;

    /**
     * 查询所有类型信息
     * @return
     */
    @Override
    public List<Route> findAllRoute() {
        return routeDao.findAllRoute();
    }
    /**
     * 添加总路径和坐标
     * @param route
     */
    @Override
    public void saveRoute(Route route) {
        routeDao.saveRoute(route);
    }
    /**
     * 根据主图ID查询类型信息
     * @return
     */
    @Override
    public List<Route> findRouteByUid(int u_id) {
        return routeDao.findRouteByUid(u_id);
    }
}
