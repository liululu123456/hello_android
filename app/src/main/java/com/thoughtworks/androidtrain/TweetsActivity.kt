package com.thoughtworks.androidtrain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.androidtrain.model.Tweet

class TweetsActivity: AppCompatActivity() {
    private var json = "[\n" +
            "  {\n" +
            "    \"content\": \"沙发！\",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage109.360doc.com%2FDownloadImg%2F2021%2F06%2F2718%2F225134712_2_20210627062006114&refer=http%3A%2F%2Fimage109.360doc.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1713599271&t=5d38af5d75fd7a7b8768a36c89cd33db\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img.pconline.com.cn/images/upload/upc/tx/itbbs/2101/25/c1/251135935_1611532823091_mthumb.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://inews.gtimg.com/om_bt/OuevRi3lDJoCccAqM17UARGbNlk9CRf3pGPv7He7zA8yYAA/1000\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"cyao\",\n" +
            "      \"nick\": \"Cheng Yao\",\n" +
            "      \"avatar\": \"https://c-ssl.dtstatic.com/uploads/blog/202104/02/20210402200403_1e37e.thumb.1000_0.jpeg\"\n" +
            "    },\n" +
            "    \"comments\": [\n" +
            "      {\n" +
            "        \"content\": \"Good.\",\n" +
            "        \"sender\": {\n" +
            "          \"username\": \"leihuang\",\n" +
            "          \"nick\": \"Lei Huang\",\n" +
            "          \"avatar\": \"https://img2.baidu.com/it/u=1206160498,3793807833&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"content\": \"Like it too\",\n" +
            "        \"sender\": {\n" +
            "          \"username\": \"weidong\",\n" +
            "          \"nick\": \"WeiDong Gu\",\n" +
            "          \"avatar\": \"https://img1.baidu.com/it/u=2162701724,1596529340&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"xinge\",\n" +
            "      \"nick\": \"Xin Ge\",\n" +
            "      \"avatar\": \"https://img0.baidu.com/it/u=7693395,537938155&fm=253&fmt=auto&app=138&f=JPEG?w=475&h=475\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://img1.baidu.com/it/u=1393202875,865399199&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"yangluo\",\n" +
            "      \"nick\": \"Yang Luo\",\n" +
            "      \"avatar\": \"https://k.sinaimg.cn/n/sinakd20111/400/w1800h1800/20240208/fd96-665d75b9f61608d3fdfdd528bcfe3ecd.jpg/w700d1q75cms.jpg\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://img2.baidu.com/it/u=3103463722,844572391&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"jianing\",\n" +
            "      \"nick\": \"Jianing Zheng\",\n" +
            "      \"avatar\": \"https://img2.baidu.com/it/u=1581709097,3484261915&fm=253&fmt=auto&app=138&f=JPEG?w=380&h=380\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"error\": \"losted\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"Unlike many proprietary big data processing platform different, Spark is built on a unified abstract RDD, making it possible to deal with different ways consistent with large data processing scenarios, including MapReduce, Streaming, SQL, Machine Learning and Graph so on. To understand the Spark, you have to understand the RDD. \",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://img2.baidu.com/it/u=305926015,1425403299&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"weifan\",\n" +
            "      \"nick\": \"Wei Fan\",\n" +
            "      \"avatar\": \"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F20181%2F31%2F2018131201849_35trz.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1713599608&t=041e2ef0971f9f863b69c5b4318136de\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"这是第二页第一条\",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://img1.baidu.com/it/u=2509062467,2628773185&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img2.baidu.com/it/u=522199070,1183693194&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://hbimg.b0.upaiyun.com/5fb6a212693903e0fa0b8214b168284e32622f9415229c-fZ8QUA_fw658\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img1.baidu.com/it/u=2573600469,1750328955&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"xinguo\",\n" +
            "      \"nick\": \"Xin Guo\",\n" +
            "      \"avatar\": \"https://img1.baidu.com/it/u=3514757328,2242742459&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500\"\n" +
            "    },\n" +
            "    \"comments\": [\n" +
            "      {\n" +
            "        \"content\": \"Good.\",\n" +
            "        \"sender\": {\n" +
            "          \"username\": \"yanzili\",\n" +
            "          \"nick\": \"Yanzi Li\",\n" +
            "          \"avatar\": \"https://img1.baidu.com/it/u=1207770372,268765688&fm=253&fmt=auto&app=138&f=JPEG?w=380&h=380\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"content\": \"Like it too\",\n" +
            "        \"sender\": {\n" +
            "          \"username\": \"jingzhao\",\n" +
            "          \"nick\": \"Jing Zhao\",\n" +
            "          \"avatar\": \"https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2022%2F0323%2F3b90401fj00r95nf7005qd200u000u0g00u000u0.jpg&thumbnail=660x2147483647&quality=80&type=jpg\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"hengzeng\",\n" +
            "      \"nick\": \"Niang Niang\",\n" +
            "      \"avatar\": \"https://img.ytxfz.com/upload/2169516.jpg\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://pic.quanjing.com/f9/ra/QJ8760940164.jpg@%21350h\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"jizhang\",\n" +
            "      \"nick\": \"Jian Zhang\",\n" +
            "      \"avatar\": \"https://img1.baidu.com/it/u=2952402435,423504532&fm=253&fmt=auto&app=138&f=JPEG?w=475&h=475\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://img2.baidu.com/it/u=2512405542,1173403233&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=750\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"yanzi\",\n" +
            "      \"nick\": \"Yanzi Li\",\n" +
            "      \"avatar\": \"https://img0.baidu.com/it/u=3482442474,919743522&fm=253&fmt=auto&app=138&f=JPEG?w=380&h=380\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"error\": \"illegal\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"error\": \"WTF\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"error\": \"WOW\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"As a programmer, we should as far as possible away from the Windows system. However, the most a professional programmer, and very difficult to bypass Windows this wretched existence but in fact very real, then how to quickly build a simple set of available windows based test environment? See Qiu Juntao's blog. \",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://img2.baidu.com/it/u=3228371152,945834326&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"jianing\",\n" +
            "      \"nick\": \"Jianing Zheng\",\n" +
            "      \"avatar\": \"https://img1.baidu.com/it/u=2457099433,3517415725&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400\"\n" +
            "    },\n" +
            "    \"comments\": [\n" +
            "      {\n" +
            "        \"content\": \"Good.\",\n" +
            "        \"sender\": {\n" +
            "          \"username\": \"cyao\",\n" +
            "          \"nick\": \"Cheng Yao\",\n" +
            "          \"avatar\": \"https://up.enterdesk.com/edpic_source/89/20/99/8920998540476c84384727265fe6e53b.jpg\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"第9条！\",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2022%2F0421%2F550943c9j00rap0m4001fc000hs00vmc.jpg&thumbnail=660x2147483647&quality=80&type=jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img2.baidu.com/it/u=2618995118,2964314412&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img2.baidu.com/it/u=3085617455,3117577265&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=705\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img2.baidu.com/it/u=4229569386,2712190763&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://pic.616pic.com/bg_w1180/00/16/37/FdnrlFXBWI.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img1.baidu.com/it/u=1150933214,1253217755&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img.wxcha.com/file/201907/26/37f2e306f0.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img9.51tietu.net/pic/2019-091119/by4wvalofyxby4wvalofyx.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://img0.baidu.com/it/u=108223765,2928662250&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=1731\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"jianing\",\n" +
            "      \"nick\": \"Jianing Zheng\",\n" +
            "      \"avatar\": \"https://img2.baidu.com/it/u=1544450321,700305520&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400\"\n" +
            "    },\n" +
            "    \"comments\": []\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"第10条！\",\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"xinguo\",\n" +
            "      \"nick\": \"Xin Guo\",\n" +
            "      \"avatar\": \"https://img0.baidu.com/it/u=70679579,790352413&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400\"\n" +
            "    },\n" +
            "    \"comments\": []\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"楼下保持队形，第11条\",\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"yanzi\",\n" +
            "      \"nick\": \"Yanzi Li\",\n" +
            "      \"avatar\": \"https://img1.baidu.com/it/u=3830269010,2418163714&fm=253&fmt=auto&app=138&f=JPEG?w=380&h=380\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"第12条\",\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"xinguo\",\n" +
            "      \"nick\": \"Xin Guo\",\n" +
            "      \"avatar\": \"https://img1.baidu.com/it/u=2826639914,751114424&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"第13条\",\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"yanzili\",\n" +
            "      \"nick\": \"Yanzi Li\",\n" +
            "      \"avatar\": \"https://img1.baidu.com/it/u=2771973749,1679628402&fm=253&fmt=auto&app=138&f=JPEG?w=380&h=380\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"第14条\",\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"xinguo\",\n" +
            "      \"nick\": \"Xin Guo\",\n" +
            "      \"avatar\": \"https://img2.baidu.com/it/u=3673065476,2629807377&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"unknown error\": \"STARCRAFT2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"content\": \"- The data json will be hosted in http://thoughtworks-ios.herokuapp.com/- Nibs or storyboards are prohibited- Implement cache mechanism by you own if needed- Unit tests is appreciated.- Functional programming is appreciated- Deployment Target should be 7.0- Utilise Git for source control, for git log is the directly evidence of the development process- Utilise GCD for multi-thread operation- Only binary, framework or cocopods dependency is allowed. do not copy other developer's source code(*.h, *.m) into your project- Keep your code clean as much as possible\",\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"hengzeng\",\n" +
            "      \"nick\": \"Huan Huan\",\n" +
            "      \"avatar\": \"https://img1.baidu.com/it/u=549953012,2699646320&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500\"\n" +
            "    }\n" +
            "  }\n" +
            "]"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)

        val gson = Gson()
        val tweetListType = object : TypeToken<List<Tweet>>() {}.type
        val dataset:List<Tweet> = gson.fromJson(json, tweetListType)
        val toDisplayData: List<Tweet> = dataset.filter { it.content != null }.toList()
        val customAdapter = CustomAdapter(toDisplayData)

        val recyclerView: RecyclerView = findViewById(R.id.tweets)
        recyclerView.adapter = customAdapter
    }




}

