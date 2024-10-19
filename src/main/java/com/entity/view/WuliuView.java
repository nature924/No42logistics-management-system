package com.entity.view;

import com.entity.WuliuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 物流信息
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("wuliu")
public class WuliuView extends WuliuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 物流状态的值
		*/
		private String wuliuValue;



		//级联表 cheliang
			/**
			* 车辆编号
			*/
			private String cheliangUuidNumber;
			/**
			* 车辆名称
			*/
			private String cheliangName;
			/**
			* 车辆类型
			*/
			private Integer cheliangTypes;
				/**
				* 车辆类型的值
				*/
				private String cheliangValue;
			/**
			* 车辆状态
			*/
			private Integer cheliangStatusTypes;
				/**
				* 车辆状态的值
				*/
				private String cheliangStatusValue;
			/**
			* 车辆详情
			*/
			private String cheliangContent;

		//级联表 siji
			/**
			* 司机姓名
			*/
			private String sijiName;
			/**
			* 头像
			*/
			private String sijiPhoto;
			/**
			* 性别
			*/
			private Integer sexTypes;
				/**
				* 性别的值
				*/
				private String sexValue;
			/**
			* 联系方式
			*/
			private String sijiPhone;

		//级联表 yundan
			/**
			* 运单信息 的 用户
			*/
			private Integer yundanYonghuId;
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
				* 运单状态的值
				*/
				private String yundanStatusValue;

	public WuliuView() {

	}

	public WuliuView(WuliuEntity wuliuEntity) {
		try {
			BeanUtils.copyProperties(this, wuliuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 物流状态的值
			*/
			public String getWuliuValue() {
				return wuliuValue;
			}
			/**
			* 设置： 物流状态的值
			*/
			public void setWuliuValue(String wuliuValue) {
				this.wuliuValue = wuliuValue;
			}




				//级联表的get和set cheliang

					/**
					* 获取： 车辆编号
					*/
					public String getCheliangUuidNumber() {
						return cheliangUuidNumber;
					}
					/**
					* 设置： 车辆编号
					*/
					public void setCheliangUuidNumber(String cheliangUuidNumber) {
						this.cheliangUuidNumber = cheliangUuidNumber;
					}

					/**
					* 获取： 车辆名称
					*/
					public String getCheliangName() {
						return cheliangName;
					}
					/**
					* 设置： 车辆名称
					*/
					public void setCheliangName(String cheliangName) {
						this.cheliangName = cheliangName;
					}

					/**
					* 获取： 车辆类型
					*/
					public Integer getCheliangTypes() {
						return cheliangTypes;
					}
					/**
					* 设置： 车辆类型
					*/
					public void setCheliangTypes(Integer cheliangTypes) {
						this.cheliangTypes = cheliangTypes;
					}


						/**
						* 获取： 车辆类型的值
						*/
						public String getCheliangValue() {
							return cheliangValue;
						}
						/**
						* 设置： 车辆类型的值
						*/
						public void setCheliangValue(String cheliangValue) {
							this.cheliangValue = cheliangValue;
						}

					/**
					* 获取： 车辆状态
					*/
					public Integer getCheliangStatusTypes() {
						return cheliangStatusTypes;
					}
					/**
					* 设置： 车辆状态
					*/
					public void setCheliangStatusTypes(Integer cheliangStatusTypes) {
						this.cheliangStatusTypes = cheliangStatusTypes;
					}


						/**
						* 获取： 车辆状态的值
						*/
						public String getCheliangStatusValue() {
							return cheliangStatusValue;
						}
						/**
						* 设置： 车辆状态的值
						*/
						public void setCheliangStatusValue(String cheliangStatusValue) {
							this.cheliangStatusValue = cheliangStatusValue;
						}

					/**
					* 获取： 车辆详情
					*/
					public String getCheliangContent() {
						return cheliangContent;
					}
					/**
					* 设置： 车辆详情
					*/
					public void setCheliangContent(String cheliangContent) {
						this.cheliangContent = cheliangContent;
					}














				//级联表的get和set siji

					/**
					* 获取： 司机姓名
					*/
					public String getSijiName() {
						return sijiName;
					}
					/**
					* 设置： 司机姓名
					*/
					public void setSijiName(String sijiName) {
						this.sijiName = sijiName;
					}

					/**
					* 获取： 头像
					*/
					public String getSijiPhoto() {
						return sijiPhoto;
					}
					/**
					* 设置： 头像
					*/
					public void setSijiPhoto(String sijiPhoto) {
						this.sijiPhoto = sijiPhoto;
					}

					/**
					* 获取： 性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
					}


						/**
						* 获取： 性别的值
						*/
						public String getSexValue() {
							return sexValue;
						}
						/**
						* 设置： 性别的值
						*/
						public void setSexValue(String sexValue) {
							this.sexValue = sexValue;
						}

					/**
					* 获取： 联系方式
					*/
					public String getSijiPhone() {
						return sijiPhone;
					}
					/**
					* 设置： 联系方式
					*/
					public void setSijiPhone(String sijiPhone) {
						this.sijiPhone = sijiPhone;
					}











				//级联表的get和set yundan

					/**
					* 获取：运单信息 的 用户
					*/
					public Integer getYundanYonghuId() {
						return yundanYonghuId;
					}
					/**
					* 设置：运单信息 的 用户
					*/
					public void setYundanYonghuId(Integer yundanYonghuId) {
						this.yundanYonghuId = yundanYonghuId;
					}


					/**
					* 获取： 送货地点
					*/
					public String getYundanAddress() {
						return yundanAddress;
					}
					/**
					* 设置： 送货地点
					*/
					public void setYundanAddress(String yundanAddress) {
						this.yundanAddress = yundanAddress;
					}

					/**
					* 获取： 收件人
					*/
					public String getYundanName() {
						return yundanName;
					}
					/**
					* 设置： 收件人
					*/
					public void setYundanName(String yundanName) {
						this.yundanName = yundanName;
					}

					/**
					* 获取： 联系方式
					*/
					public String getYundanPhone() {
						return yundanPhone;
					}
					/**
					* 设置： 联系方式
					*/
					public void setYundanPhone(String yundanPhone) {
						this.yundanPhone = yundanPhone;
					}

					/**
					* 获取： 运单状态
					*/
					public Integer getYundanStatusTypes() {
						return yundanStatusTypes;
					}
					/**
					* 设置： 运单状态
					*/
					public void setYundanStatusTypes(Integer yundanStatusTypes) {
						this.yundanStatusTypes = yundanStatusTypes;
					}


						/**
						* 获取： 运单状态的值
						*/
						public String getYundanStatusValue() {
							return yundanStatusValue;
						}
						/**
						* 设置： 运单状态的值
						*/
						public void setYundanStatusValue(String yundanStatusValue) {
							this.yundanStatusValue = yundanStatusValue;
						}





}
