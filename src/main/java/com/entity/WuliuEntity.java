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
 * 物流信息
 *
 * @author 
 * @email
 */
@TableName("wuliu")
public class WuliuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WuliuEntity() {

	}

	public WuliuEntity(T t) {
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
     * 物流单号
     */
    @TableField(value = "wuliu_uuid_number")

    private String wuliuUuidNumber;


    /**
     * 车辆
     */
    @TableField(value = "cheliang_id")

    private Integer cheliangId;


    /**
     * 运单
     */
    @TableField(value = "yundan_id")

    private Integer yundanId;


    /**
     * 负责司机
     */
    @TableField(value = "siji_id")

    private Integer sijiId;


    /**
     * 物流状态
     */
    @TableField(value = "wuliu_types")

    private Integer wuliuTypes;


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
	 * 设置：物流单号
	 */
    public String getWuliuUuidNumber() {
        return wuliuUuidNumber;
    }
    /**
	 * 获取：物流单号
	 */

    public void setWuliuUuidNumber(String wuliuUuidNumber) {
        this.wuliuUuidNumber = wuliuUuidNumber;
    }
    /**
	 * 设置：车辆
	 */
    public Integer getCheliangId() {
        return cheliangId;
    }
    /**
	 * 获取：车辆
	 */

    public void setCheliangId(Integer cheliangId) {
        this.cheliangId = cheliangId;
    }
    /**
	 * 设置：运单
	 */
    public Integer getYundanId() {
        return yundanId;
    }
    /**
	 * 获取：运单
	 */

    public void setYundanId(Integer yundanId) {
        this.yundanId = yundanId;
    }
    /**
	 * 设置：负责司机
	 */
    public Integer getSijiId() {
        return sijiId;
    }
    /**
	 * 获取：负责司机
	 */

    public void setSijiId(Integer sijiId) {
        this.sijiId = sijiId;
    }
    /**
	 * 设置：物流状态
	 */
    public Integer getWuliuTypes() {
        return wuliuTypes;
    }
    /**
	 * 获取：物流状态
	 */

    public void setWuliuTypes(Integer wuliuTypes) {
        this.wuliuTypes = wuliuTypes;
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
        return "Wuliu{" +
            "id=" + id +
            ", wuliuUuidNumber=" + wuliuUuidNumber +
            ", cheliangId=" + cheliangId +
            ", yundanId=" + yundanId +
            ", sijiId=" + sijiId +
            ", wuliuTypes=" + wuliuTypes +
            ", createTime=" + createTime +
        "}";
    }
}
