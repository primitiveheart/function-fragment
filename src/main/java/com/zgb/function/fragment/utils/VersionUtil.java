package com.zgb.function.fragment.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 版本比较工具
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2020/9/6 11:01 上午
 * @Created By guanbao.zhou
 */
public class VersionUtil {
    public static String VERSION_MATCH_STRING = "([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.*([0-9]*)";
    public static Pattern VERSION_PATTERN;

    public VersionUtil() {
    }

    public static int compare(String version, String nowVersion) {
      int[] oldV = parseVersion(version);
      int[] nowV = parseVersion(nowVersion);
      int flag = 0;

      for(int i = 0; i < oldV.length; ++i) {
        if (oldV[i] > nowV[i]) {
          return 1;
        }

        if (oldV[i] != nowV[i] && oldV[i] < nowV[i]) {
          return -1;
        }
      }

      return flag;
    }

    public static int[] parseVersion(String version) {
      int[] v = new int[4];
      if (StringUtils.isEmpty(version)) {
        return v;
      } else {
        Matcher matcher = VERSION_PATTERN.matcher(version);
        if (!matcher.matches()) {
          throw new IllegalArgumentException("error version format:" + version);
        } else {
          for(int i = 0; i < matcher.groupCount(); ++i) {
            try {
              String m = matcher.group(i + 1);
              if (StringUtils.isEmpty(m)) {
                v[i] = 0;
              } else {
                v[i] = Integer.parseInt(m);
              }
            } catch (NumberFormatException var5) {
              throw new IllegalArgumentException();
            }
          }

          return v;
        }
      }
    }

    public static boolean isValidVersion(String version) {
      return VERSION_PATTERN.matcher(version).matches();
    }

    public static void main(String[] args) {
      System.out.println(compare("12.1.12", "12.0.12.1"));
      List<String> versionList = new ArrayList();
      versionList.add("2.1.4.1");
      versionList.add("1.1.4.1");
      versionList.add("2.12.4.1");
      versionList.add("0.1.4.1");
      versionList.add("2.1.4.13");
      versionList.add("12.1.4.1");
      versionList.add("2.14.4.1");
      Collections.sort(versionList, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
          return VersionUtil.compare(o1, o2);
        }
      });
      System.out.println(JSON.toJSONString(versionList));
    }

    static {
      VERSION_PATTERN = Pattern.compile(VERSION_MATCH_STRING);
    }
}
