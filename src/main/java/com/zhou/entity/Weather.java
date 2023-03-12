package com.zhou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 周志刚
 * @Date 2022/8/22 9:27
 * @PackageName: com.zhou.entity
 * @ClassName: Weather
 * @Description: 天气实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

        String wd_night;
        String date;
        String high;
        String week;
        String text_night;
        String wd_day;
        String low;
        String wc_night;
        String text_day;
        String wc_day;
        // 当前天气
        String text_now;
        // 当前温度
        String temp;
        // 风级大小
        String wind_class;
        // 风向
        String wind_dir;

}
