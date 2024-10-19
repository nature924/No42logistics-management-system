package com.entity.model;

import com.entity.YundanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 运单信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YundanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 送货地点
     */
    private String yundanAddress;


    /**
     * 收件人
     */
    private String yundanName;


    /**
     * 联系方式
     */
    private String yundanPhone;


    /**
     * 运单状态
     */
    private Integer yundanStatusTypes;


    /**
     * 创建时间 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：送货地点
	 */
    public String getYundanAddress() {
        return yundanAddress;
    }


    /**
	 * 设置：送货地点
	 */
    public void setYundanAddress(String yundanAddress) {
        this.yundanAddress = yundanAddress;
    }
    /**
	 * 获取：收件人
	 */
    public String getYundanName() {
        return yundanName;
    }


    /**
	 * 设置：收件人
	 */
    public void setYundanName(String yundanName) {
        this.yundanName = yundanName;
    }
    /**
	 * 获取：联系方式
	 */
    public String getYundanPhone() {
        return yundanPhone;
    }


    /**
	 * 设置：联系方式
	 */
    public void setYundanPhone(String yundanPhone) {
        this.yundanPhone = yundanPhone;
    }
    /**
	 * 获取：运单状态
	 */
    public Integer getYundanStatusTypes() {
        return yundanStatusTypes;
    }


    /**
	 * 设置：运单状态
	 */
    public void setYundanStatusTypes(Integer yundanStatusTypes) {
        this.yundanStatusTypes = yundanStatusTypes;
    }
    /**
	 * 获取：创建时间 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
