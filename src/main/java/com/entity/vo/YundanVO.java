package com.entity.vo;

import com.entity.YundanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 运单信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yundan")
public class YundanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 送货地点
     */

    @TableField(value = "yundan_address")
    private String yundanAddress;


    /**
     * 收件人
     */

    @TableField(value = "yundan_name")
    private String yundanName;


    /**
     * 联系方式
     */

    @TableField(value = "yundan_phone")
    private String yundanPhone;


    /**
     * 运单状态
     */

    @TableField(value = "yundan_status_types")
    private Integer yundanStatusTypes;


    /**
     * 创建时间 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：送货地点
	 */
    public String getYundanAddress() {
        return yundanAddress;
    }


    /**
	 * 获取：送货地点
	 */

    public void setYundanAddress(String yundanAddress) {
        this.yundanAddress = yundanAddress;
    }
    /**
	 * 设置：收件人
	 */
    public String getYundanName() {
        return yundanName;
    }


    /**
	 * 获取：收件人
	 */

    public void setYundanName(String yundanName) {
        this.yundanName = yundanName;
    }
    /**
	 * 设置：联系方式
	 */
    public String getYundanPhone() {
        return yundanPhone;
    }


    /**
	 * 获取：联系方式
	 */

    public void setYundanPhone(String yundanPhone) {
        this.yundanPhone = yundanPhone;
    }
    /**
	 * 设置：运单状态
	 */
    public Integer getYundanStatusTypes() {
        return yundanStatusTypes;
    }


    /**
	 * 获取：运单状态
	 */

    public void setYundanStatusTypes(Integer yundanStatusTypes) {
        this.yundanStatusTypes = yundanStatusTypes;
    }
    /**
	 * 设置：创建时间 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
