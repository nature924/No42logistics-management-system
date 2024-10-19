
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
 * 物流信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/wuliu")
public class WuliuController {
    private static final Logger logger = LoggerFactory.getLogger(WuliuController.class);

    @Autowired
    private WuliuService wuliuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private CheliangService cheliangService;
    @Autowired
    private SijiService sijiService;
    @Autowired
    private YundanService yundanService;

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
        PageUtils page = wuliuService.queryPage(params);

        //字典表数据转换
        List<WuliuView> list =(List<WuliuView>)page.getList();
        for(WuliuView c:list){
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
        WuliuEntity wuliu = wuliuService.selectById(id);
        if(wuliu !=null){
            //entity转view
            WuliuView view = new WuliuView();
            BeanUtils.copyProperties( wuliu , view );//把实体数据重构到view中

                //级联表
                CheliangEntity cheliang = cheliangService.selectById(wuliu.getCheliangId());
                if(cheliang != null){
                    BeanUtils.copyProperties( cheliang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheliangId(cheliang.getId());
                }
                //级联表
                SijiEntity siji = sijiService.selectById(wuliu.getSijiId());
                if(siji != null){
                    BeanUtils.copyProperties( siji , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSijiId(siji.getId());
                }
                //级联表
                YundanEntity yundan = yundanService.selectById(wuliu.getYundanId());
                if(yundan != null){
                    BeanUtils.copyProperties( yundan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYundanId(yundan.getId());
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
    public R save(@RequestBody WuliuEntity wuliu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wuliu:{}",this.getClass().getName(),wuliu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<WuliuEntity> queryWrapper = new EntityWrapper<WuliuEntity>()
            .eq("wuliu_uuid_number", wuliu.getWuliuUuidNumber())
            .eq("cheliang_id", wuliu.getCheliangId())
            .eq("yundan_id", wuliu.getYundanId())
            .eq("siji_id", wuliu.getSijiId())
            .eq("wuliu_types", wuliu.getWuliuTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WuliuEntity wuliuEntity = wuliuService.selectOne(queryWrapper);
        if(wuliuEntity==null){
            wuliu.setCreateTime(new Date());
            wuliuService.insert(wuliu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WuliuEntity wuliu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,wuliu:{}",this.getClass().getName(),wuliu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<WuliuEntity> queryWrapper = new EntityWrapper<WuliuEntity>()
            .notIn("id",wuliu.getId())
            .andNew()
            .eq("wuliu_uuid_number", wuliu.getWuliuUuidNumber())
            .eq("cheliang_id", wuliu.getCheliangId())
            .eq("yundan_id", wuliu.getYundanId())
            .eq("siji_id", wuliu.getSijiId())
            .eq("wuliu_types", wuliu.getWuliuTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WuliuEntity wuliuEntity = wuliuService.selectOne(queryWrapper);
        if(wuliuEntity==null){
            wuliuService.updateById(wuliu);//根据id更新
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
        wuliuService.deleteBatchIds(Arrays.asList(ids));
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
            List<WuliuEntity> wuliuList = new ArrayList<>();//上传的东西
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
                            WuliuEntity wuliuEntity = new WuliuEntity();
//                            wuliuEntity.setWuliuUuidNumber(data.get(0));                    //物流单号 要改的
//                            wuliuEntity.setCheliangId(Integer.valueOf(data.get(0)));   //车辆 要改的
//                            wuliuEntity.setYundanId(Integer.valueOf(data.get(0)));   //运单 要改的
//                            wuliuEntity.setSijiId(Integer.valueOf(data.get(0)));   //负责司机 要改的
//                            wuliuEntity.setWuliuTypes(Integer.valueOf(data.get(0)));   //物流状态 要改的
//                            wuliuEntity.setCreateTime(date);//时间
                            wuliuList.add(wuliuEntity);


                            //把要查询是否重复的字段放入map中
                                //物流单号
                                if(seachFields.containsKey("wuliuUuidNumber")){
                                    List<String> wuliuUuidNumber = seachFields.get("wuliuUuidNumber");
                                    wuliuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> wuliuUuidNumber = new ArrayList<>();
                                    wuliuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("wuliuUuidNumber",wuliuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //物流单号
                        List<WuliuEntity> wuliuEntities_wuliuUuidNumber = wuliuService.selectList(new EntityWrapper<WuliuEntity>().in("wuliu_uuid_number", seachFields.get("wuliuUuidNumber")));
                        if(wuliuEntities_wuliuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WuliuEntity s:wuliuEntities_wuliuUuidNumber){
                                repeatFields.add(s.getWuliuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [物流单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        wuliuService.insertBatch(wuliuList);
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
