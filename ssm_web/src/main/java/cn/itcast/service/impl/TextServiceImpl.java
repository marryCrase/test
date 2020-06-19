package cn.itcast.service.impl;

import cn.itcast.dao.ITextDao;
import cn.itcast.domain.Text;
import cn.itcast.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:16:19
 */
@Service("textService")
public class TextServiceImpl implements ITextService {

    @Autowired
    private ITextDao textDao;

    /**
     * 添加文字
     * @param text
     * @return
     */
    @Override
    public void saveText(Text text) {
        textDao.saveText(text);
    }

    /**
     * 根据主图ID查询文字信息
     * @return List
     * @throws Exception
     */
    @Override
    public List<Text> findTextByUid(int u_id) {
        return textDao.findTextByUid(u_id);
    }
}
