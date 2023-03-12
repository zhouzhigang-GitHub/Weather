package com.zhou.service;

import com.zhou.entity.Weather;
import com.zhou.utils.CaiHongPiUtil;
import com.zhou.utils.JiNianRiUtil;
import com.zhou.utils.WeatherUtil;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.Map;

/**
 * @Author 周志刚
 * @Date 2022/8/22 9:32
 * @PackageName: com.zhou.service
 * @ClassName: Pusher
 * @Description: TODO
 */
public class Pusher {
    public static void main(String[] args) {
        push();
    }
    private static String appId = "wx793f23840d4f6aa1";
    private static String secret = "279887278b58dc23035b8a77beea4a34";



    public static void push(){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("of50m6LGU4VCY1b9be7xkBm_NOIo")
                .templateId("Owmixr1RXDRJx1hQHLG17Y3UolTrtAGb0V7bmadQZHs")
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        Weather weather = WeatherUtil.getWeather();
        Map<String, String> map = CaiHongPiUtil.getEnsentence();
        templateMessage.addData(new WxMpTemplateData("riqi",weather.getDate() + "  "+ weather.getWeek(),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",weather.getText_now(),"#3333FF"));
        templateMessage.addData(new WxMpTemplateData("low",weather.getLow() + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("temp",weather.getTemp() + "","#EE212D"));
        templateMessage.addData(new WxMpTemplateData("high",weather.getHigh()+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("windclass",weather.getWind_class()+ "","#42B857" ));
        templateMessage.addData(new WxMpTemplateData("winddir",weather.getWind_dir()+ "","#B95EA3" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi",CaiHongPiUtil.getCaiHongPi(),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRiUtil.getLianAi()+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri",JiNianRiUtil.getBirthday_Jo()+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("en",map.get("en") +"","#C71585"));
        templateMessage.addData(new WxMpTemplateData("zh",map.get("zh") +"","#C71585"));
        String beizhu = "宇宙超级无敌可爱美少女";
        if(JiNianRiUtil.getLianAi() % 365 == 0){
            beizhu = "今天是恋爱" + (JiNianRiUtil.getLianAi() / 365) + "周年纪念日！";
        }
        if(JiNianRiUtil.getBirthday_Jo()  == 0){
            beizhu = "今天是生日，生日快乐呀！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));

        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

}
