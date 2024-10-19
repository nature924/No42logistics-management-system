
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 运单信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yundan")
public class YundanController {
    private static final Logger logger = LoggerFactory.getLogger(YundanController.class);

    @Autowired
    private YundanService yundanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = yundanService.queryPage(params);

        //字典表数据转换
        List<YundanView> list =(List<YundanView>)page.getList();
        for(YundanView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YundanEntity yundan = yundanService.selectById(id);
        if(yundan !=null){
            //entity转view
            YundanView view = new YundanView();
            BeanUtils.copyProperties( yundan , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(yundan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YundanEntity yundan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yundan:{}",this.getClass().getName(),yundan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yundan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<YundanEntity> queryWrapper = new EntityWrapper<YundanEntity>()
            .eq("yonghu_id", yundan.getYonghuId())
            .eq("yundan_address", yundan.getYundanAddress())
            .eq("yundan_name", yundan.getYundanName())
            .eq("yundan_phone", yundan.getYundanPhone())
            .eq("yundan_status_types", yundan.getYundanStatusTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YundanEntity yundanEntity = yundanService.selectOne(queryWrapper);
        if(yundanEntity==null){
            yundan.setCreateTime(new Date());
            yundanService.insert(yundan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YundanEntity yundan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yundan:{}",this.getClass().getName(),yundan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yundan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<YundanEntity> queryWrapper = new EntityWrapper<YundanEntity>()
            .notIn("id",yundan.getId())
            .andNew()
            .eq("yonghu_id", yundan.getYonghuId())
            .eq("yundan_address", yundan.getYundanAddress())
            .eq("yundan_name", yundan.getYundanName())
            .eq("yundan_phone", yundan.getYundanPhone())
            .eq("yundan_status_types", yundan.getYundanStatusTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YundanEntity yundanEntity = yundanService.selectOne(queryWrapper);
        if(yundanEntity==null){
            yundanService.updateById(yundan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yundanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<YundanEntity> yundanList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YundanEntity yundanEntity = new YundanEntity();
//                            yundanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yundanEntity.setYundanAddress(data.get(0));                    //送货地点 要改的
//                            yundanEntity.setYundanName(data.get(0));                    //收件人 要改的
//                            yundanEntity.setYundanPhone(data.get(0));                    //联系方式 要改的
//                            yundanEntity.setYundanStatusTypes(Integer.valueOf(data.get(0)));   //运单状态 要改的
//                            yundanEntity.setCreateTime(date);//时间
                            yundanList.add(yundanEntity);


                            //把要查询是否重复的字段放入map中
                                //联系方式
                                if(seachFields.containsKey("yundanPhone")){
                                    List<String> yundanPhone = seachFields.get("yundanPhone");
                                    yundanPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> yundanPhone = new ArrayList<>();
                                    yundanPhone.add(data.get(0));//要改的
                                    seachFields.put("yundanPhone",yundanPhone);
                                }
                        }

                        //查询是否重复
                         //联系方式
                        List<YundanEntity> yundanEntities_yundanPhone = yundanService.selectList(new EntityWrapper<YundanEntity>().in("yundan_phone", seachFields.get("yundanPhone")));
                        if(yundanEntities_yundanPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YundanEntity s:yundanEntities_yundanPhone){
                                repeatFields.add(s.getYundanPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yundanService.insertBatch(yundanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
