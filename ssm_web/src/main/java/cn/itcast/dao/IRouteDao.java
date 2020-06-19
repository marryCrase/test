package cn.itcast.dao;

import cn.itcast.domain.Route;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:25
 */
@Repository("routeDao")
public interface IRouteDao {

    /**
     * 查询所有类型信息
     * @return
     */
    @Select("select * from p_route")
    List<Route> findAllRoute();

    /**
     * 根据主图ID查询类型信息
     * @return
     */
    @Select("select * from p_route where u_id=#{u_id}")
    List<Route> findRouteByUid(int u_id);

    /**
     * 添加总路径和坐标
     * @param route
     */
    @Insert("insert into p_route values(null,#{type},#{url},#{route},#{u_id})")
    void saveRoute(Route route);
}
