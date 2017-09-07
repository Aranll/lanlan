package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.PublicNumber;

import java.util.List;

/**
 * 公众号Dao<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface PublicNumberDao {

    /**
     * 保存公众号
     * @param publicNumber 必填：name，qrCode，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int save(PublicNumber publicNumber);

    /**
     * 删除公众号
     * @param publicNumber 必填：id
     * @return 受影响行数
     */
    int remove(PublicNumber publicNumber);

    /**
     * 更新公众号
     * @param publicNumber 必填：id，可选更新字段：name，qrCode，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int update(PublicNumber publicNumber);

    /**
     * 获取单个公众号
     * @param publicNumber 必填：id
     * @return 公众号，字段：所有，category.id，category.name
     */
    PublicNumber get(PublicNumber publicNumber);

    /**
     * 获取多个公众号<br/>
     * 支持排序和分页
     * @param publicNumber 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 公众号列表，字段：所有，category.id，category.name
     */
    List<PublicNumber> list(PublicNumber publicNumber);

    /**
     * 获取多个公众号<br/>
     * 支持排序和分页
     * @param publicNumber 可选条件：name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 公众号列表，字段：id，name，qrCode，category.id
     */
    List<PublicNumber> listCombo(PublicNumber publicNumber);

    /**
     * 统计公众号数量
     * @param publicNumber 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 公众号数量
     */
    Long count(PublicNumber publicNumber);
}
