package cn.itcast.service;

import cn.itcast.domain.Route;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:25
 */
public interface IRouteService {

    /**
     * 查询所有类型信息
     * @return
     */
    List<Route> findAllRoute();

    /**
     * 添加总路径和坐标
     * @param route
     */
    void saveRoute(Route route);

    /**
     * 根据主图ID查询类型信息
     * @return
     */
    public List<Route> findRouteByUid(int u_id);


}
