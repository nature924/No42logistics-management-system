package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 运单信息
 *
 * @author 
 * @email
 */
@TableName("yundan")
public class YundanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YundanEntity() {

	}

	public YundanEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Yundan{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", yundanAddress=" + yundanAddress +
            ", yundanName=" + yundanName +
            ", yundanPhone=" + yundanPhone +
            ", yundanStatusTypes=" + yundanStatusTypes +
            ", createTime=" + createTime +
        "}";
    }
}
