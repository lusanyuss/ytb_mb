package com.yuliu.util;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ChannelSection;
import com.google.api.services.youtube.model.ChannelSectionListResponse;
import com.google.api.services.youtube.model.GuideCategory;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.ThumbnailDetails;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.yuliu.activity.AppApplication;
import com.yuliu.bean.MyPlayList;
import com.yuliu.bean.PageData;
import com.yuliu.bean.PageItem;
import com.yuliu.bean.PageViewHolder;
import com.yuliu.bean.adapter.PlayListsAdapter;
import com.yuliu.bean.adapter.VideoAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import me.maxwin.view.XListView;

public class UIHelper {
    protected static final Long line = 10L;
    private volatile static UIHelper instance;
    // public final HashSet<String> isLoadingSet = new HashSet<String>();
    public final int MORE = 1;
    public final int REFRESH = 0;
    public final ArrayList<GuideCategory> M_GUIDE_CATEGORIES = new ArrayList<GuideCategory>();
    public final HashMap<String, PageData> HASH_MAP = new HashMap<String, PageData>();
    public final HashMap<String, ChannelListResponse> mChannelCache = new HashMap<String, ChannelListResponse>();
    public final HashMap<String, PlaylistItemListResponse> mPlayListItemCache = new HashMap<String, PlaylistItemListResponse>();
    public final HashMap<String, PlaylistListResponse> mPlayListCache = new HashMap<String, PlaylistListResponse>();
    public final String[][] mChannels =
            {
                    {
                            "Popular", "UCF0pVplsI8R5kcAqgtoRqoA", "menu_bg_2"
                    },
                    {
                            "Music", "UC-9-kyTW8ZkZNDHQJ6FgpwQ", "menu_bg_3"
                    },
                    {
                            "360Video", "UCzuqhhs6NWbgTzMuM09WKDQ", "menu_bg_1"
                    },
                    {
                            "Spotlight", "UCBR8-60-B28hp2BmDPdntcQ", "menu_bg_7"
                    },
                    {
                            "Sports", "UCEgdi0XIXXZ-qJOFPf4JSKw", "menu_bg_4"
                    },
                    {
                            "Gaming", "UCOpNcN46UbXVtpKMrmU4Abg", "menu_bg_5"
                    },
                    {
                            "Education", "UC3yA8nDwraeOfnYfBWun83g", "menu_bg_6"
                    }

            };

    private UIHelper() {
    }

    public static UIHelper getInstance() {
        if (instance == null) {
            synchronized (UIHelper.class) {
                if (instance == null) {
                    instance = new UIHelper();
                }
            }
        }
        return instance;
    }

    public List<MyPlayList> getHomeById(String id) {
        List<MyPlayList> mPlaylists = new ArrayList<MyPlayList>();
        try {
            YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName(Auth.appName).build();

            YouTube.Playlists.List mList = youtube.playlists().list("id,snippet,contentDetails");
            mList.setKey(Auth.apiKey);
            StringBuilder mBuilder = new StringBuilder();

            // Popular
            if (id.equals("UCF0pVplsI8R5kcAqgtoRqoA")) {

                mBuilder.append("PLrEnWoR732-BHrPp_Pm8_VleD68f9s14-").append(",");
                mBuilder.append("PLrEnWoR732-D67iteOI6DPdJH1opjAuJt").append(",");
                mBuilder.append("PLrEnWoR732-AryY0r5haGYownNGHANSyX").append(",");
                mBuilder.append("PLrEnWoR732-DN561GnxXKMlocLMc4v4jL").append(",");
                mBuilder.append("PLrEnWoR732-AMp_tf9DDKAP_Vymn8zqh3").append(",");
                mBuilder.append("PLrEnWoR732-B36nvkIy_PY2WYQuofL7kw").append(",");
                mBuilder.append("PLrEnWoR732-AKYdZyzAnuf-MnPiw7rT4Q").append(",");
                mBuilder.append("PLrEnWoR732-CFfUX4TPybGxiO7q_5OS8D").append(",");
                mBuilder.append("PLrEnWoR732-CN09YykVof2lxdI3MLOZda").append(",");
                mBuilder.append("PLrEnWoR732-CvU2EIng1mKhlXJHvaiAVM").append(",");
                mBuilder.append("PLrEnWoR732-D6uerjQ8dZiyy9bJID58CK").append(",");
                mBuilder.append("PLrEnWoR732-BddQaHT4O-FOeaGyicL_ER").append(",");
                mBuilder.append("PLrEnWoR732-AtjHiRPQD7-Xaqa3SaXo-4").append(",");
                mBuilder.append("PLrEnWoR732-C6CxLMz_oRf19VzGgxF2bT").append(",");
                mBuilder.append("PLrEnWoR732-DZV1Jc8bUpVTF_HTPbywpE").append(",");
                mBuilder.append("PLrEnWoR732-Dq8q-pDT52yq6RkXcCkF99").append(",");
                mBuilder.append("PLrEnWoR732-DAtB4qI6uwhHALM7zyP9vU").append(",");
                mBuilder.append("PLrEnWoR732-A5O0m6ut9IIZFVef1uz2YC").append(",");
                mBuilder.append("PLrEnWoR732-CV75Y0BCvbVyGDtjoghNEg").append(",");
                mBuilder.append("PLrEnWoR732-Auu_lB2IMmwSClClXs4Btn").append(",");
                mBuilder.append("PLrEnWoR732-CT660q6ItMkluWHXoktR98").append(",");
                mBuilder.append("PLrEnWoR732-CLErl7IpTyA2-sZz5g58mw").append(",");


            }
            // Music
            else if (id.equals("UC-9-kyTW8ZkZNDHQJ6FgpwQ")) {
                mBuilder.append("PLFgquLnL59akA2PflFpeQG9L01VFg90wS").append(",");
                mBuilder.append("PLFgquLnL59alW3xmYiWRaoz0oM3H17Lth").append(",");
                mBuilder.append("PLFgquLnL59alCl_2TQvOiD5Vgm1hCaGSI").append(",");
                mBuilder.append("PLDcnymzs18LVXfO_x0Ei0R24qDbVtyy66").append(",");
                mBuilder.append("PLFgquLnL59ak1rAcy0P3SqO2rtidlRtFP").append(",");
                mBuilder.append("PLFgquLnL59amnyqxA4V3m5f98tQSYwi83").append(",");
                mBuilder.append("PLH6pfBXQXHECUaIU3bu9rjG2L6Uhl5A2q").append(",");
                mBuilder.append("PLtYHnS0mhkb_WVLIVwCErFLnjtJrSozkt").append(",");
                mBuilder.append("PLcfQmtiAG0X_byEjBwRXaGJ9WoJL_ntNr").append(",");
                mBuilder.append("PLFgquLnL59alK03ZaGA3CL4Pq7CwWwSfB").append(",");
                mBuilder.append("PLlAUeZBl7BV6lWENNsb78zZrgyzmOBwrq").append(",");
                mBuilder.append("PLq-ZRVZ1W4FcimhT61WDkh71_3CDL1KBD").append(",");
                mBuilder.append("PL0zQrw6ZA60bl5Cq6cv83xp8E38oIzosA").append(",");
                mBuilder.append("PLFgquLnL59aldqu6uJMvSDSdVm7nKOXRy").append(",");
                mBuilder.append("PLQog_FHUHAFVRsO4otlwzn0bZspSAefOl").append(",");
                mBuilder.append("PLvLX2y1VZ-tEmqtENBW39gdozqFCN_WZc").append(",");
                mBuilder.append("PLFgquLnL59alq_j8zF0tJOohLlHl4gmFl").append(",");
                mBuilder.append("PLFgquLnL59ak5gmnz28ZiMd59ryeTPXjT").append(",");
                mBuilder.append("PLhd1HyMTk3f5yqcPXjLo8qroWJiMMFBSk").append(",");
                mBuilder.append("PLFPg_IUxqnZN1odUAOrgMifStf-0VZ-lj").append(",");
                mBuilder.append("PLFgquLnL59amxzoMDgMC5OJhTmRP9gi4B").append(",");
                mBuilder.append("PLFbKRa_kS4i852ZBCW0XyrVEZzanMFY2a").append(",");
                mBuilder.append("PLMcThd22goGZoKIj4VAX4GCoYjoCNLiTC").append(",");
                mBuilder.append("PLFgquLnL59an_uWPEnsQ9JPpjlLHPhizv").append(",");
                mBuilder.append("PLFgquLnL59am8Rl9AyGzal4sZN7QbhBOS").append(",");
                mBuilder.append("PLzauiyXIK7Rj1h23BPvDb3sQwmzHhRuyX").append(",");
                mBuilder.append("PLI39zLOagDhxUAsY315e1ERavyUqZ6ovS").append(",");
                mBuilder.append("PLLMA7Sh3JsORlZSt9eMT49ejfGYrY0IKG").append(",");
                mBuilder.append("PLo5cIhJ0-8jnS_E2ZG0Je2Dt_jQuvgP_Y").append(",");
                mBuilder.append("PLVXq77mXV53_3HqhCLGv4mz3oVGYd2Aup").append(",");
                mBuilder.append("PLVAJ90ZhCcL92eB13TrTIXWbBTpG5ZR0L").append(",");
                mBuilder.append("PLNDw0dvPrjjcEIv7Q-LE5of7uUOmZ2tgD").append(",");
                mBuilder.append("PLJ0Pbja2awaNscjVUAzmED4RwxpwbCPYd").append(",");
                mBuilder.append("PLYAYp5OI4lRKUjxyMxpxZvSJsJLJBu0XH").append(",");
                mBuilder.append("PLo2ccPasj2cC4-6_5ypVo5gZeddI2SLLt").append(",");
                mBuilder.append("PLzOnHpG7pFWJoAgEMyCpi8btTYPvzwTU5").append(",");
                mBuilder.append("PLfY-m4YMsF-OSodOIoTKPVblnYvdjU2Tn").append(",");
                mBuilder.append("PLWNXn_iQ2yrIE-txPYCsmdmRJv-iSTPsL").append(",");
            }

            // Sports
            else if (id.equals("UCEgdi0XIXXZ-qJOFPf4JSKw")) {
                mBuilder.append("PL8fVUTBmJhHJDAtZwiIOooPRurN0hna-j").append(",");
                mBuilder.append("PL8fVUTBmJhHJmpP7sLb9JfLtdwCmYX9xC").append(",");
                mBuilder.append("PLuDrvcGJQS7OsNR0qOFltdG_RU55rN7ph").append(",");
                mBuilder.append("PL8fVUTBmJhHJlT40aNl_cX4qcrVqoC5eg").append(",");
                mBuilder.append("PL8fVUTBmJhHITxZ2AlYoL-fi9tBNRfc_D").append(",");
                mBuilder.append("PL8fVUTBmJhHKnpYEhDm90TAjjKE5vsQqS").append(",");
                mBuilder.append("PL8fVUTBmJhHKig9Gj1JA_MX_ZVPYqhjKW").append(",");
                mBuilder.append("PL2B-PWFn-iOYg_zxsr4v62rQ4aaapT4P6").append(",");
                mBuilder.append("PL16FrVP89GvsQUZbsopF5W4dt8qS1kXO1").append(",");
                mBuilder.append("PL1ZcZsep3ZaOPf47uErRQ-z12GBjoXbtE").append(",");
                mBuilder.append("PLWL89d_alIhEHl-AWL0D5UZfEkzlzkKTG").append(",");
                mBuilder.append("PL8fVUTBmJhHK5QJttd-A62OqBzmCjRaPh").append(",");

            }

            // Gaming
            else if (id.equals("UCOpNcN46UbXVtpKMrmU4Abg")) {

                mBuilder.append("PLiCvVJzBupKnKoAJR3T8NxXwA5mPeBD8W").append(",");
                mBuilder.append("PLiCvVJzBupKm1kmQhBDlkTuS2kN-yU-Dh").append(",");
                mBuilder.append("PLfKYDLgOQjN2HqDt-togIiKeNtUEB9Jkg").append(",");
                mBuilder.append("PLZKuf2oBY67hSKfuuorXzyin-JTPOKwlC").append(",");
                mBuilder.append("PLr8KIbGK44Lv8A7O6MJSadw_PhdlI4ttR").append(",");
                mBuilder.append("PLQxCpAfK-cck_W_W85SUEX8arlaQ4c6Al").append(",");
                mBuilder.append("PLiCvVJzBupKkGAtAL9luLD93BW_WFRGZ7").append(",");
                mBuilder.append("PLiCvVJzBupKmqXaW_FoBPuwstGWia1cyz").append(",");
                mBuilder.append("PLiCvVJzBupKmb-JolA0ytI_1MZz_DLqtY").append(",");
                mBuilder.append("PLiCvVJzBupKmDlfcKcXwGKhFkhdE254Kw").append(",");
            }

            // Education
            else if (id.equals("UC3yA8nDwraeOfnYfBWun83g")) {
                mBuilder.append("PLN_VEYjh8gCDWntOJs9EYgRblpV2ED5nq").append(",");
                mBuilder.append("PLN_VEYjh8gCA2oyBolBNttH5Wmo35Uo3z").append(",");
                mBuilder.append("PLN_VEYjh8gCCK8MuM7Du4PXFQI-aIVTq0").append(",");
                mBuilder.append("PLN_VEYjh8gCB6fRKIiIy6lDh92rvTwF09").append(",");
                mBuilder.append("PLN_VEYjh8gCCPCDyMrNo2jFhWZRfqumf5").append(",");
                mBuilder.append("PLN_VEYjh8gCC6CsDXOexwh0i-cYCT6u0w").append(",");
                mBuilder.append("PLN_VEYjh8gCAgreQ4aNiKbPD9pEGIdG-A").append(",");
                mBuilder.append("PLN_VEYjh8gCBCbjwDjihHtoxBf3aJD8KL").append(",");
                mBuilder.append("PLN_VEYjh8gCB7inFDEpvJOe5nlRvJWVl4").append(",");

            }
            // Spotlight
            else if (id.equals("UCBR8-60-B28hp2BmDPdntcQ")) {

                mBuilder.append("PLN_VEYjh8gCDWntOJs9EYgRblpV2ED5nq").append(",");

            }// 360Video
            else if (id.equals("UCzuqhhs6NWbgTzMuM09WKDQ")) {
                mBuilder.append("PLU8wpH_LfhmvMokgsfQtiHNsP96bU7cnr").append(",");
                mBuilder.append("PLU8wpH_LfhmsOCrpscf8V4nkQcQk8b61E").append(",");
                mBuilder.append("PLU8wpH_Lfhmsg812V7gz6ggmOOnB62zg_").append(",");
                mBuilder.append("PLU8wpH_LfhmvDSgh9kaj1QUxscdMWU5FH").append(",");
                mBuilder.append("PLU8wpH_LfhmuLcA0kgwMWL9wD7LpAdXwM").append(",");
                mBuilder.append("PLU8wpH_Lfhmt8PEzms0OG-ocoemmdpWqZ").append(",");
                mBuilder.append("PLU8wpH_LfhmstlTdI69-LGf9FFdFuQAe1").append(",");
                mBuilder.append("PLU8wpH_LfhmuJmeZSCmv6V-5doO0qBjvA").append(",");
                mBuilder.append("PLU8wpH_LfhmsJRSPovwdiXkFUavhEKfNh").append(",");
                mBuilder.append("PLU8wpH_LfhmtvQys2OOCzXcTiiufL5P81").append(",");
                mBuilder.append("PLU8wpH_Lfhmt6yxLtfwdxrhytCltNiy2r").append(",");
                mBuilder.append("PLU8wpH_Lfhmudsn9rfhlWBjKYX-zN5F2i").append(",");
            }

            // else if (id.equals("sexy"))
            // {
            //
            // mBuilder.append("PLkgkeIjk-8gQgAl6vuLP8UHN5pgfCAjz3,");
            // mBuilder.append("PL17FE47B86D4F8A9A,");
            // mBuilder.append("PLkgkeIjk-8gRgq8fXX-lOsaKJAAC66TKJ,");
            // mBuilder.append("PLkgkeIjk-8gSBriyaq513GC3chM3LcYAW,");
            // mBuilder.append("PLkgkeIjk-8gRf1bWSjjbmywnKhZivVkY-,");
            // mBuilder.append("PLkgkeIjk-8gSUibcdqPx_Rrc3PRhFvsMs,");
            // mBuilder.append("PL8395C4E27B95B6DC,");
            // mBuilder.append("PL486A28FE62B9246A,");
            // mBuilder.append("PLE7D9C2983FAF70CB,");
            // mBuilder.append("PLkgkeIjk-8gTOzaY8zt02-oMrU4qdwGi_,");
            // mBuilder.append("PLkgkeIjk-8gShOG3gjQX-zeGLzyZ2a2WX,");
            // mBuilder.append("PL5sXgfXY70dp-9fYmso4K9Zdii15z85wj,");
            // mBuilder.append("PLgsqdD2rFp7S6_cUYnUh5Rl3X45akoHKo,");
            // mBuilder.append("PLluQTQGsNfnggOPkI2WqwA_Y2x30o-slF,");
            // mBuilder.append("PLVaFB7YYgEKxxiYC_eDdD7PYfu1dQNIrw,");
            // mBuilder.append("PL8-wH9wOuS5q7wS7vleFaynM2_NnTNbu5,");
            // mBuilder.append("PLZ-qXI8PFP2DUhAcyxlPSgj7q0XxgurUv,");
            // mBuilder.append("PLgQqtYb-l6-vhv-bQmbSWj3KOctGuD3dS,");
            // mBuilder.append("PL8CA03433B5ADDA1A,");
            // mBuilder.append("PL4upQoc1MXN2Lu3w0KkJD45EM-x1LPq6U,");
            // mBuilder.append("PL51245B790032FB25,");
            // mBuilder.append("PLmUAuZEhB1OOQkfPSv_UGhBc5nxhJ0GxJ,");
            // mBuilder.append("PLIN_TTVKJ08KOjZgnsdOCSEEzyI7E-yDm,");
            // mBuilder.append("PL525J-WLLObQhE0vtJc4Cz_BQyPgJEfn0,");
            // mBuilder.append("PLmyVchEI_OmZYjHjBT4J7IGFLW9knYUs7,");
            // mBuilder.append("PLzwIf8A3jkSyJpTcl61Zg8xGzJ_rdINA5,");
            // mBuilder.append("PLNnKFwvvm7ZwOthWGEGybTx_lKFFKjbp1,");
            // mBuilder.append("PLIN_TTVKJ08IHJq0Fk1fsj5tNgxw4OcyR,");
            // mBuilder.append("PLiuvEoSwPiqvHoVAKYciX05NvCoW9QiV-,");
            // mBuilder.append("PL4468B6ADEF30FE18,");
            // mBuilder.append("PLE63868C68B989132,");
            // mBuilder.append("PLhUjW_TvAEB1F-HVD4XRJYBiabsKCHLAj,");
            // mBuilder.append("PLP0Pxtqt4jz1Sfq-IkcQlVYw2CZH72oys,");
            // mBuilder.append("PLkgkeIjk-8gSuI-EE1-Lo2zhplqjOaZQT,");
            // mBuilder.append("PLkgkeIjk-8gQs7sn_1C77t24j1t1tlGEt,");
            //
            // }
            // else if (id.equals("beauty"))
            // {
            // // 挑逗
            //
            // mBuilder.append("PL8EESTg-Y4ODvX6l-zOhgvqFpdXm4KuLs,");
            // mBuilder.append("PLF6uxldpQM7yA8lzga8pzdR8oy6fqtGzE,");
            // mBuilder.append("PL8EESTg-Y4OAOas-dLzV4bIrLWdsmF5DM,");
            // mBuilder.append("PLWM6Mb-z8M_T6iABYIFtaY7xbVIsmzLtT,");
            // mBuilder.append("PL25uRJPWepblcPBr9vg9oFQ8-Z4wf7NPX,");
            // mBuilder.append("PL4ypuAMic-GhqQK-mX8U7DnxFcLEjCr-q,");
            // mBuilder.append("PLHDGyBCtWr54od1W1lFcPqLftIe0n6euO,");
            // mBuilder.append("PL1E4zSRQH5NTc9IzTbRqvOa8BMDrvquWz,");
            // mBuilder.append("PLMwaKIa_vS948gfRvjfqm5y1F652uVXLk,");
            // mBuilder.append("PL5uwBG7d8RMYpl-wL_UMy8vRIbzijMfuK,");
            // mBuilder.append("PLmXif25nmZj9-gL0zMjwlOCIBHypXm8Dm,");
            // mBuilder.append("PLF5DB17B629062A58,");
            // mBuilder.append("PLA4A7C74C36354ED8&index=136,");
            // mBuilder.append("PL8319D0F599AC2707,");
            // mBuilder.append("PLA4A7C74C36354ED8,");
            // mBuilder.append("PLWgAYyFyYilsLAk1Rqo209AHOzDzSuBuk,");
            // mBuilder.append("PL9E0BCF124D0C8443,");
            // // av
            // mBuilder.append("PLgsqdD2rFp7S6_cUYnUh5Rl3X45akoHKo,");
            // mBuilder.append("PLFjaWrkEUc3fZtuFzbZ4ixlpNlNfXyJjL,");
            // mBuilder.append("PL42D299090BEDFA55,");
            // mBuilder.append("PLA84F177962B277B5,");
            // mBuilder.append("PLFXJcNytiCpCZ6pnbvKrS_REkNoue1Xgy,");
            // mBuilder.append("PL8118ED6875EE5291,");
            // mBuilder.append("PL4CDAA71BC7BB8132,");
            // mBuilder.append("PLFjaWrkEUc3fZtuFzbZ4ixlpNlNfXyJjL,");
            // mBuilder.append("PL83082660E4AEAB6D,");
            // mBuilder.append("PL5326ECDE876C54D5,");
            //
            // mBuilder.append("PL04E7E9566DC3F3F7,");
            // mBuilder.append("PL5893B3F6904F52F6,");
            // mBuilder.append("PLE2FC21191430EFF0,");
            // mBuilder.append("PLF601667158E594F8,");
            // mBuilder.append("PLVaFB7YYgEKw_JyWd3Q2AqKrw6nlW8TKM,");
            // mBuilder.append("PL2C5L3EQEZaJ2D8ZMwbvR5IO-lzcvXw6y,");
            // mBuilder.append("PLVaFB7YYgEKyw_WBzeqqHAM7IMyw0YvsY,");
            // mBuilder.append("PLoDmiLzQNxYaZlKt1ETFMwILsf0YgX6Cb,");
            // mBuilder.append("PLgsqdD2rFp7S79c0DddXljqFa1WQQqE93,");
            // mBuilder.append("PLC0AC3F2D80763347,");
            // mBuilder.append("PL0CBD3CDE0088B61B,");
            // mBuilder.append("PLF98EE03AAE6A20CE,");
            // mBuilder.append("PLE70BDCBEDCA2A721,");
            // mBuilder.append("PLDFA0D900AD49370B,");
            //
            // }
            // else
            // {
            // YouTube.ChannelSections.List mChannelSections =
            // youtube.channelSections().list(
            // "id,snippet,contentDetails");
            // mChannelSections.setKey(Auth.apiKey);
            // mChannelSections.setChannelId(id);
            // ChannelSectionListResponse mChannelListResponse =
            // mChannelSections.execute();
            // List<ChannelSection> mSections = mChannelListResponse.getItems();
            // int i = 0;
            // for (ChannelSection channelSection : mSections)
            // {
            // // if (i > 20)
            // // {
            // // break;
            // // }
            // if
            // ("singlePlaylist".equals(channelSection.getSnippet().getType()))
            // {
            // if (channelSection.getContentDetails().getPlaylists() != null
            // && channelSection.getContentDetails().getPlaylists().size() > 0)
            // {
            // String playListId =
            // channelSection.getContentDetails().getPlaylists().get(0);
            // mBuilder.append(playListId + ",");
            // i++;
            // }
            //
            // }
            // }
            // }

            mBuilder.deleteCharAt(mBuilder.length() - 1);
            mList.setId(mBuilder.toString());
            PlaylistListResponse mItemListResponse = mList.execute();

            List<Playlist> playlists = mItemListResponse.getItems();
            for (Playlist playlist : playlists) {
                MyPlayList myPlayList = new MyPlayList();
                myPlayList.id = playlist.getId();
                myPlayList.title = playlist.getSnippet().getTitle();
                mPlaylists.add(myPlayList);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return mPlaylists;
    }

    private void getLists(String id, YouTube youtube, StringBuilder mBuilder) throws IOException {
        YouTube.ChannelSections.List mChannelSections = youtube.channelSections().list("id,snippet,contentDetails");
        mChannelSections.setKey(Auth.apiKey);
        mChannelSections.setChannelId(id);
        ChannelSectionListResponse mChannelListResponse = mChannelSections.execute();
        List<ChannelSection> mSections = mChannelListResponse.getItems();
        int i = 0;
        for (ChannelSection channelSection : mSections) {
            if ("singlePlaylist".equals(channelSection.getSnippet().getType())) {
                if (channelSection.getContentDetails().getPlaylists() != null
                        && channelSection.getContentDetails().getPlaylists().size() > 0) {
                    String playListId = channelSection.getContentDetails().getPlaylists().get(0);
                    mBuilder.append(playListId + ",");
                    i++;
                }
            }
        }
    }

    public void onLoad(XListView mListView) {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        long mtime = Long.valueOf(System.currentTimeMillis());
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm:ss");
        mListView.setRefreshTime(dateformat.format(new Date(mtime)));
    }

    // public static void getChannelsInit(final String categoryId, final String
    // pageToken, final ChannelsAdapter adapter,
    // final int action)
    // {
    //
    // new AsyncTask<Void, Void, ChannelListResponse>()
    // {
    // @Override
    // protected ChannelListResponse doInBackground(Void... params)
    // {
    //
    // String key = categoryId + "_" + pageToken;
    // ChannelListResponse response = null;
    //
    // System.out.println(mChannelCache.containsKey(key));
    // if (mChannelCache.containsKey(key) && mChannelCache.get(key) != null
    // && mChannelCache.get(key).size() > 0)
    // {
    // response = mChannelCache.get(key);
    // return response;
    // }
    // else
    // {
    // try
    // {
    // YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT,
    // Auth.JSON_FACTORY,
    // new HttpRequestInitializer()
    // {
    // public void initialize(HttpRequest request) throws IOException
    // {
    // }
    // }).setApplicationName("yltube").build();
    //
    // YouTube.Channels.List mList =
    // youtube.channels().list("id,snippet,contentDetails,statistics");
    // mList.setMaxResults(50L);
    // if (pageToken != null && !pageToken.trim().equals(""))
    // {
    // mList.setPageToken(pageToken);
    // }
    //
    // mList.setCategoryId(categoryId);
    // mList.setKey(Auth.apiKey);
    // response = mList.execute();
    //
    // }
    // catch (Exception e)
    // {
    // // TODO: handle exception
    // }
    // // 保存缓存
    // if (response != null && response.size() > 0)
    // {
    // mChannelCache.put(key, response);
    // }
    // return response;
    // }
    //
    // }
    //
    // @Override
    // protected void onPostExecute(ChannelListResponse mResponse)
    // {
    // // TODO Auto-generated method stub
    // super.onPostExecute(mResponse);
    //
    // if (mResponse != null)
    // {
    // List<Channel> mChannels = mResponse.getItems();
    // if (mChannels != null && mChannels.size() > 0)
    // {
    // adapter.mResponse = mResponse;
    // if (action == REFRESH)
    // {
    // adapter.mList.clear();
    // }
    //
    // Channel[] mChannels2 = new Channel[mChannels.size()];
    // if (adapter.postion == 0)
    // {
    // for (Channel channel : mChannels)
    // {
    // if (channel.getId().equals("UCF0pVplsI8R5kcAqgtoRqoA"))
    // {
    // mChannels2[0] = channel;
    // }
    // else if (channel.getId().equals("UC-9-kyTW8ZkZNDHQJ6FgpwQ"))
    // {
    // mChannels2[1] = channel;
    // }
    // else if (channel.getId().equals("UCBR8-60-B28hp2BmDPdntcQ"))
    // {
    // mChannels2[2] = channel;
    // }
    // else if (channel.getId().equals("UCOpNcN46UbXVtpKMrmU4Abg"))
    // {
    // mChannels2[3] = channel;
    // }
    // else if (channel.getId().equals("UCYfdidRxbB8Qhf0Nx7ioOYw"))
    // {
    // mChannels2[4] = channel;
    // }
    // else if (channel.getId().equals("UCEgdi0XIXXZ-qJOFPf4JSKw"))
    // {
    // mChannels2[5] = channel;
    // }
    // else if (channel.getId().equals("UC3yA8nDwraeOfnYfBWun83g"))
    // {
    // mChannels2[6] = channel;
    // }
    //
    // else if (channel.getId().equals("UCczhp4wznQWonO7Pb8HQ2MQ"))
    // {
    // mChannels2[7] = channel;
    // }
    //
    // else if (channel.getId().equals("UCl8dMTqDrJQ0c8y23UBu4kQ"))
    // {
    // mChannels2[8] = channel;
    // }
    // }
    //
    // for (int i = 0; i < mChannels2.length; i++)
    // {
    // adapter.mList.add(mChannels2[i]);
    // }
    // }
    // else
    // {
    // adapter.mList.addAll(mChannels);
    // }
    // adapter.notifyDataSetChanged();
    //
    // }
    // }
    //
    // UIHelper.isLoading = false;
    //
    // }
    //
    // @Override
    // protected void onCancelled()
    // {
    // // TODO Auto-generated method stub
    // super.onCancelled();
    // UIHelper.isLoading = false;
    //
    // }
    //
    // }.execute();
    // }

    // public static void getChannels(final String categoryId, final String
    // pageToken, final ChannelsAdapter adapter,
    // final int action)
    // {
    //
    // if (UIHelper.isLoading)
    // {
    // return;
    // }
    // getChannelsInit(categoryId, pageToken, adapter, action);
    //
    // }

    public void getPlayLists(final String relatedPlaylists, final String pageToken, final PlayListsAdapter adapter,
                             final int action) {
        new AsyncTask<Void, Void, PlaylistListResponse>() {
            @Override
            protected PlaylistListResponse doInBackground(Void... params) {

                PlaylistListResponse response = null;
                try {
                    YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY,
                            new HttpRequestInitializer() {
                                public void initialize(HttpRequest request) throws IOException {
                                }
                            }).setApplicationName(Auth.appName).build();

                    YouTube.Playlists.List mList = youtube.playlists().list("id,snippet");
                    mList.setMaxResults(50L);
                    if (pageToken != null && !pageToken.trim().equals("")) {
                        mList.setPageToken(pageToken);
                    }
                    mList.setKey(Auth.apiKey);
                    mList.setChannelId(relatedPlaylists);
                    response = mList.execute();

                } catch (Exception e) {
                    // TODO: handle exception
                }
                return response;
            }

            @Override
            protected void onPostExecute(PlaylistListResponse mResponse) {
                // TODO Auto-generated method stub
                super.onPostExecute(mResponse);

                if (mResponse != null) {
                    List<Playlist> mPlaylists = mResponse.getItems();
                    if (mPlaylists != null && mPlaylists.size() > 0) {
                        adapter.mResponse = mResponse;
                        if (action == UIHelper.instance.REFRESH) {
                            adapter.mList.clear();
                        }
                        adapter.mList.addAll(mPlaylists);
                        adapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            protected void onCancelled() {
                // TODO Auto-generated method stub
                super.onCancelled();

            }

        }.execute();
    }

    public void getPlayListItems(final AppApplication mApplication, final String playlistId, final String pageToken,
                                 final VideoAdapter adapter, final PageViewHolder holder, final int action) {

        final String key = playlistId + "_" + pageToken;
        final String time_key = key + "time";
        String value = SharedHelper.getPlayListsItem(mApplication, key);
        final long oldTime = SharedHelper.getPlayListsItemTime(mApplication, time_key);
        final long newTime = System.currentTimeMillis();
        holder.progressBar1.setVisibility(View.VISIBLE);
        if (mApplication.isNetworkConnected() && (newTime - oldTime > MyContants.CacheTime || TextUtils.isEmpty(value))) {
            new AsyncTask<Void, Void, PageData>() {
                @Override
                protected PageData doInBackground(Void... params) {

                    PageData mPageData = new PageData();
                    try {
                        YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY,
                                new HttpRequestInitializer() {
                                    public void initialize(HttpRequest request) throws IOException {
                                    }
                                }).setApplicationName(Auth.appName).build();

                        YouTube.PlaylistItems.List mList = youtube.playlistItems().list("snippet");
                        mList.setMaxResults(line);
                        if (pageToken != null && !TextUtils.isEmpty(pageToken)) {
                            mList.setPageToken(pageToken);
                        }
                        mList.setKey(Auth.apiKey);
                        mList.setPlaylistId(playlistId);
                        PlaylistItemListResponse mItemListResponse = mList.execute();
                        List<PlaylistItem> mItems = mItemListResponse.getItems();
                        mPageData.nextPageToken = mItemListResponse.getNextPageToken();

                        StringBuffer mStringBuffer = new StringBuffer();
                        for (PlaylistItem playlistItem : mItems) {
                            mStringBuffer.append(playlistItem.getSnippet().getResourceId().getVideoId() + ",");
                        }
                        mStringBuffer.deleteCharAt(mStringBuffer.length() - 1);

                        List<PageItem> mVideos = new ArrayList<PageItem>();
                        PageItem mPageItem = null;
                        ThumbnailDetails mThumbnailDetails;
                        Thumbnail mThumbnail;

                        YouTube.Videos.List mVideoList = youtube.videos().list("snippet,contentDetails,statistics");
                        mVideoList.setKey(Auth.apiKey);
                        mVideoList.setId(mStringBuffer.toString());
                        VideoListResponse mVideoListResponse = mVideoList.execute();
                        List<Video> mAllVideos2 = mVideoListResponse.getItems();

                        StringBuilder mCacheString = new StringBuilder();
                        for (Video video : mAllVideos2) {
                            mPageItem = new PageItem();
                            mPageItem.videoid = video.getId();
                            mThumbnailDetails = video.getSnippet().getThumbnails();
                            if (mThumbnailDetails != null) {
                                mThumbnail = mThumbnailDetails.getHigh();
                                if (mThumbnail != null) {
                                    if (!TextUtils.isEmpty(mThumbnail.getUrl())) {
                                        mPageItem.imageurl = mThumbnail.getUrl();
                                    }
                                }
                            }

                            if (!TextUtils.isEmpty(video.getSnippet().getTitle())) {
                                mPageItem.title = video.getSnippet().getTitle();
                            }

                            mPageItem.description = video.getSnippet().getDescription();

                            if (video.getSnippet().getPublishedAt() != null) {
                                mPageItem.publishedAt = String.valueOf(video.getSnippet().getPublishedAt().getValue());
                            }

                            mPageItem.duration = video.getContentDetails().getDuration();
                            mPageItem.viewCount = video.getStatistics().getViewCount().toString();
                            mPageItem.likeCount = video.getStatistics().getLikeCount().toString();
                            mPageItem.dislikeCount = video.getStatistics().getDislikeCount().toString();
                            mPageItem.favoriteCount = video.getStatistics().getFavoriteCount().toString();
                            mPageItem.commentCount = video.getStatistics().getCommentCount().toString();

                            mCacheString.append(mPageItem.videoid + MyContants.FGF + mPageItem.imageurl
                                    + MyContants.FGF + mPageItem.title + MyContants.FGF + mPageItem.description
                                    + MyContants.FGF + mPageItem.publishedAt + MyContants.FGF + mPageItem.duration
                                    + MyContants.FGF + mPageItem.viewCount + MyContants.FGF + mPageItem.likeCount
                                    + MyContants.FGF + mPageItem.dislikeCount + MyContants.FGF
                                    + mPageItem.favoriteCount + MyContants.FGF + mPageItem.commentCount
                                    + MyContants.FGF);

                            mVideos.add(mPageItem);

                        }

                        mCacheString.append(mPageData.nextPageToken);

                        mPageData.mResults = mVideos;

                        // 缓存数据
                        if (mPageData.mResults != null && mPageData.mResults.size() > 0) {
                            SharedHelper.setPlayListsItemTime(mApplication, time_key, System.currentTimeMillis());
                            SharedHelper.setPlayListsItem(mApplication, key, mCacheString.toString());
                        }

                    } catch (Exception e) {
                    }
                    return mPageData;

                }

                @Override
                protected void onPostExecute(PageData mPageData) {
                    // TODO Auto-generated method stub
                    super.onPostExecute(mPageData);
                    holder.progressBar1.setVisibility(View.GONE);
                    rfreash(adapter, action, mPageData);

                }

                @Override
                protected void onCancelled() {
                    // TODO Auto-generated method stub
                    super.onCancelled();
                    holder.progressBar1.setVisibility(View.GONE);
                }

            }.execute();
        } else {
            PageData mPageData = new PageData();
            String[] mStrings = value.split(MyContants.FGF);
            mPageData.nextPageToken = mStrings[mStrings.length - 1];
            for (int i = 0; i < mStrings.length - 1; i = i + 11) {
                PageItem mItem = new PageItem();
                mItem.videoid = mStrings[i];
                mItem.imageurl = mStrings[i + 1];
                mItem.title = mStrings[i + 2];
                mItem.description = mStrings[i + 3];
                mItem.publishedAt = mStrings[i + 4];
                mItem.duration = mStrings[i + 5];
                mItem.viewCount = mStrings[i + 6];
                mItem.likeCount = mStrings[i + 7];
                mItem.dislikeCount = mStrings[i + 8];
                mItem.favoriteCount = mStrings[i + 9];
                mItem.commentCount = mStrings[i + 10];
                mPageData.mResults.add(mItem);
            }
            rfreash(adapter, action, mPageData);
            holder.progressBar1.setVisibility(View.GONE);
        }

    }

    private void rfreash(final VideoAdapter adapter, final int action, PageData mPageData) {
        if (mPageData != null) {
            if (mPageData.mResults != null && mPageData.mResults.size() > 0) {
                if (adapter.nextPageToken != null && mPageData.nextPageToken != null
                        && mPageData.nextPageToken.equals(adapter.nextPageToken)) {
                    return;
                }

                if (mPageData.nextPageToken != null) {
                    adapter.currentPageToken = adapter.nextPageToken;
                    adapter.nextPageToken = mPageData.nextPageToken;
                } else {
                    adapter.nextPageToken = null;
                }
                if (action == UIHelper.instance.REFRESH) {
                    adapter.mList.clear();
                }

                adapter.mList.addAll(mPageData.mResults);
                adapter.notifyDataSetChanged();
            }
        }
    }

    // public static void getPlayListItems(final AppApplication mApplication,
    // final String playlistId,
    // final String pageToken, final VideoAdapter adapter, final PageViewHolder
    // holder, final int action)
    // {
    // if (UIHelper.isLoading)
    // {
    // return;
    // }
    // getPlayListItemsInit(mApplication, playlistId, pageToken, adapter,
    // holder, action);
    // }

    public void ShowAd(final AdView mAdView) {
        mAdView.loadAd(getAdRequest());
    }

    public AdRequest getAdRequest() {
        AdRequest adRequest = new AdRequest.Builder().setGender(AdRequest.GENDER_MALE)
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("865002026743054")
                .addTestDevice("99000628935437").addTestDevice("460010980500940").addTestDevice("A0000048391EBE")
                .build();
        return adRequest;
    }

}
