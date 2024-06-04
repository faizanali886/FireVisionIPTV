package com.cadnative.firevisioniptv;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class fileReader {

    private static final String TAG = "FileReader";
    private static final String EXT_INF_SP = "#EXTINF:";
    private static final String KOD_IP_DROP_TYPE = "#KODIPROP:inputstream.adaptive.license_type=";
    private static final String KOD_IP_DROP_KEY = "#KODIPROP:inputstream.adaptive.license_key=";
    private static final String TVG_NAME = "tvg-name=";
    private static final String TVG_LOGO = "tvg-logo=";
    private static final String GROUP_TITLE = "group-title=";
    private static final String COMMA = ",";
    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    private final List<Channel> channelList;

    public fileReader() {
        this.channelList = new ArrayList<>();
    }

    public List<Channel> readFile() {
        String text = "#EXTM3U\n" +
                "#EXTINF:-1 tvg-id=\"7SMusic.in\",7S Music (576p) [Not 24/7]\n" +
                "http://103.199.161.254/Content/7smusic/Live/Channel(7smusic)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"9XM.in\",9XM (480p)\n" +
                "https://d2q8p4pe5spbak.cloudfront.net/bpk-tv/9XM/9XM.isml/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"A1TVRajasthan.in\",A1 TV Rajasthan (720p)\n" +
                "https://5b48d7e1b4bce.streamlock.net/myapp/a1live/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AajTak.in\",Aaj Tak\n" +
                "https://feeds.intoday.in/aajtak/api/aajtakhd/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AajTak.in\",Aaj Tak (404p)\n" +
                "https://aajtaklive-amd.akamaized.net/hls/live/2014416/aajtak/aajtaklive/live_404p/chunks.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AajTak.in\",Aaj Tak (360p) [Geo-blocked]\n" +
                "https://lmil.live-s.cdn.bitgravity.com/cdn-live/_definst_/lmil/live/aajtak_app.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Aastha.in\",Aastha (480p)\n" +
                "https://aasthaott.akamaized.net/110923/smil:aasthatv.smil/chunklist_b1328000.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AasthaBhajan.in\",Aastha Bhajan (480p)\n" +
                "#EXTVLCOPT:http-referrer=Aasthatv.in\n" +
                "https://aasthaott.akamaized.net/110923/smil:bhajan.smil/chunklist_b264000.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AathavanTV.uk\",Aathavan TV (720p) [Not 24/7]\n" +
                "http://45.77.66.224:1935/athavantv/live/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ABPGanga.in\",ABP Ganga (1080p)\n" +
                "https://abplivetv.akamaized.net/hls/live/2043013/ganga/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ABPMajha.in\",ABP Majha (720p)\n" +
                "https://abplivetv.akamaized.net/hls/live/2043011/manjha/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ABPNews.in\",ABP News (720p)\n" +
                "https://abplivetv.akamaized.net/hls/live/2043010/hindi/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ABPSanjha.in\",ABP Sanjha (720p)\n" +
                "https://d316coox0if60r.cloudfront.net/out/v1/7723f2221c3449c3910aaa8f91592bf0/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Adipoli.in\",Adipoli (1080p)\n" +
                "https://live.hungama.com/linear/adipoli/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AJNetNews.in\",AJ Net News [Not 24/7]\n" +
                "http://rtmp.logicwebs.in:1935/live/ajnet/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AkaramKidz.in\",Akaram Kidz (460p) [Not 24/7]\n" +
                "http://akaram.zecast.net/akaram-live/akaramkidz/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AKDCalcuttaNews.in\",AKD Calcutta News (540p) [Geo-blocked]\n" +
                "https://d39iawgzv3h0yo.cloudfront.net/out/v1/1ef4344a3b4a41908915d58ac7bd5e23/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AmritaTV.in\",Amrita TV (720p)\n" +
                "https://dr1zhpsuem5f4.cloudfront.net/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVAfrica.in\",Angel TV Africa (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelafrica_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVAmerica.in\",Angel TV America (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelamerica_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVArabia.in\",Angel TV Arabia (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelarabia_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVAustralia.in\",Angel TV Australia (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelaustralia_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVChinese.in\",Angel TV Chinese (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelchinese_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVEurope.in\",Angel TV Europe (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angeleurope_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVFarEast.in\",Angel TV Far East (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelfareast_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVHebrew.in\",Angel TV Hebrew (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelhebrew_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVIndia.in\",Angel TV India (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelindia_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVPortuguese.in\",Angel TV Portuguese (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelportuguese_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AngelTVSpanish.in\",Angel TV Spanish (720p)\n" +
                "https://cdn3.wowza.com/5/TDJ0aWNkNXFxWWta/angeltvcloud/ngrp:angelspanish_all/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AnjanTV.in\",Anjan (720p) [Not 24/7]\n" +
                "https://f3.vstream.online:7443/bstb/ngrp:anjan_hdall/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ApnaPunjabTV.ca\",Apna Punjab TV (720p)\n" +
                "https://plus.gigabitcdn.net/live-stream/apna-punjab-H3sE/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ArgusNews.in\",Argus News (1080p)\n" +
                "https://live.argusnews.in/hls/argustv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ArtistAloud.in\",Artist Aloud (1080p) [Not 24/7]\n" +
                "https://live.hungama.com/linear/artist-aloud/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AshrafiChannel.in\",Ashrafi Channel (484p) [Not 24/7]\n" +
                "https://ashrafichannel.livebox.co.in/ashrafivhannelhls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AsianetNews.in\",Asianet News (360p)\n" +
                "https://vidcdn.vidgyor.com/asianet-origin/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AsianetNewsKannada.in\",Asianet News Kannada (360p) [Not 24/7]\n" +
                "https://vidcdn.vidgyor.com/suvarna-origin/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AsianetNewsTamil.in\",Asianet News Tamil (360p) [Not 24/7]\n" +
                "https://vidcdn.vidgyor.com/ptm-origin/aslive/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AyushTV.in\",Ayush TV (360p) [Not 24/7]\n" +
                "https://95eryw39dwn4-hls-live.wmncdn.net/Ayushu/271ddf829afeece44d8732757fba1a66.sdp/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"B4UKadak.in\",B4U Kadak (576p) [Not 24/7]\n" +
                "http://103.199.160.85/Content/moviehouse/Live/Channel(MovieHouse)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"B4UMoviesIndia.in\",B4U Movies India (576p)\n" +
                "http://183.89.246.119:8881/play/a08n/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"B4UMusicIndia.in\",B4U Music India (576p)\n" +
                "http://183.89.246.119:8881/play/a08o/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"B4UPlus.in\",B4U Plus (576p)\n" +
                "http://183.89.246.119:8881/play/a08l/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"BadaKhabar.in\",Bada Khabar (720p)\n" +
                "https://badakhabar.livebox.co.in/Badakhabarhls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"BalleBalle.in\",Balle Balle (720p)\n" +
                "https://mcncdndigital.com/balleballetv/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"BharatSamacharTV.in\",Bharat Samachar TV (360p)\n" +
                "https://idvd.multitvsolution.com/idvo/bharatsamachar.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"AaseervathamTV.in\",Blessing TV Tamil (720p)\n" +
                "https://stream.blessingtv.tv:1443/blessingtv-tamillive/mystream_720p/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"BoogleBollywood.in\",Boogle Bollywood (1080p) [Not 24/7]\n" +
                "http://live.agmediachandigarh.com/booglebollywood/774e3ea9f3fa9bcdac47f445b83b6653.sdp/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"BtvNews.in\",BTV News (720p) [Not 24/7]\n" +
                "https://vidcdn.vidgyor.com/btv-origin/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"CalvaryTV.in\",Calvary TV (720p)\n" +
                "https://player.mslivestream.net/mslive/71e7f7cc7dc7c51206b7b3bbb51e3052.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"CaptainTV.in\",Captain TV (576p) [Not 24/7]\n" +
                "http://103.199.160.85/Content/captain/Live/Channel(Captain)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"CCV.in\",CCV (480p)\n" +
                "https://5a1178b42cc03.streamlock.net/8212/8212/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"CDNews.in\",CD News (576p)\n" +
                "https://5a1178b42cc03.streamlock.net/8174/8174/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ChannelDivya.in\",Channel Divya (720p)\n" +
                "https://mcncdndigital.com/divyatv/index.fmp4.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ChannelWIN.in\",Channel WIN (360p)\n" +
                "https://streamer.winfoundation.in:8081/live/winstream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ChannelY.in\",Channel Y (720p) [Not 24/7]\n" +
                "http://cdn19.live247stream.com/channely/tv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"CNBCBajar.in\",CNBC Bajar (504p) [Geo-blocked]\n" +
                "https://cnbcbazar-lh.akamaihd.net/i/cnbcbajar_1@178933/index_5_av-p.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsCineplexBollywood.in\",Colors Cineplex Bollywood (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_Cineplex_Bollywood_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsCineplexSuperhits.in\",Colors Cineplex Superhits (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Rishtey_Cineplex_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Colors.in\",Colors HD (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_HD_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsInfinity.in\",Colors Infinity (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_Infinity_HD_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsKannada.in\",Colors Kannada (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_Kannada_HD_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsKannadaCinema.in\",Colors Kannada Cinema (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_Kannada_Cinema_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsMarathi.in\",Colors Marathi (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_Marathi_HD_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsRishteyAsia.in\",Colors Rishtey Asia (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_Rishtey_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsSuper.in\",Colors Super (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_Super_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ColorsTamil.in\",Colors Tamil (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Colors_Tamil_HD_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ComedyCentral.in\",Comedy Central India (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Comedy_Central_HD_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DDNews.in\",DD News (1080p)\n" +
                "https://hls.media.nic.in/hls/live/ddnews/ddnews.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DDSports.in\",DD Sports (576p)\n" +
                "http://103.199.161.254/Content/ddsports/Live/Channel(DDSPORTS)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DesiChannel.in\",Desi Channel (720p)\n" +
                "https://live.wmncdn.net/desichannel/7e2dd0aed46b70a5c77f4affdb702e4b.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Dhamaal.in\",Dhamaal (1080p) [Not 24/7]\n" +
                "https://live.hungama.com/linear/dhamaal/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DheeranTV.in\",Dheeran TV (576p)\n" +
                "https://live.we2live.in/dheerantv/dheerantv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Dhinchaak.in\",Dhinchaak (576p) [Not 24/7]\n" +
                "http://14.199.164.20:4001/play/a0o5/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DighvijayNews24x7.in\",Dighvijay (240p) [Not 24/7]\n" +
                "https://vidcdn.vidgyor.com/dighvijay-origin/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DilSe.in\",Dil Se (360p) [Not 24/7]\n" +
                "https://live.hungama.com/linear/dil-se/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DoctorLive.in\",Doctor Live (720p)\n" +
                "https://5a1178b42cc03.streamlock.net/8250/8250/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DY365.in\",DY 365 (360p)\n" +
                "https://cdn.smartstream.video/smartstream-us/dy365/dy365/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"EagleOneNews.in\",Eagle One News (576p)\n" +
                "https://5a1178b42cc03.streamlock.net/8182/8182/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"EETTV.in\",EET TV (1080p) [Not 24/7]\n" +
                "https://live.streamjo.com/eetlive/eettv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"EnlightNews.in\",Enlight News (1080p)\n" +
                "https://live.enlightnews.com/live/enlightnews/tracks-v1a1/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Enter10Bangla.in\",Enter10 Bangla (576p) [Not 24/7]\n" +
                "http://14.199.164.20:4001/play/a0j5/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ETNow.in\",ET Now (720p)\n" +
                "https://etnowweblive.akamaized.net/hls/live/2034678/ETNOWMSL4/chunklist_2.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ETNow.in\",ET Now (720p)\n" +
                "https://pubads.g.doubleclick.net/ssai/event/pJrzNyDoT_K_GwYQsijTsQ/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ETNowSwadesh.in\",ET Now Swadesh (720p)\n" +
                "https://d32gxr3r1ksq2p.cloudfront.net/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"FaktMarathi.in\",Fakt Marathi (576p) [Not 24/7]\n" +
                "http://14.199.164.20:4001/play/a0q8/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Filmeraa.in\",Filmeraa (720p)\n" +
                "https://a.jsrdn.com/broadcast/7ef91d3d7a/+0000/c.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"FirstIndiaNews.in\",First India News (360p) [Not 24/7]\n" +
                "https://xlbor37ydvaj-hls-live.wmncdn.net/firstindianewstv1/live.stream/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"FlowersTV.in\",Flowers TV (576p)\n" +
                "http://103.199.161.254/Content/flowers/Live/Channel(Flowers)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"FoodFood.in\",Food Food (576p) [Not 24/7]\n" +
                "http://14.199.164.20:4001/play/a0qx/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"FoxTV.in\",Fox TV (720p) [Not 24/7]\n" +
                "http://stream-live.in:11037/hls/foxtv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GDNSLudhiana.in\",GDNS Ludhiana (1080p) [Not 24/7]\n" +
                "http://akalmultimedia.net:1935/gdnslive/gdns-live/chunklist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GlobalPunjab.in\",Global Punjab (1080p)\n" +
                "https://server.livelegitpro.in/globalpunjab/globalpunjab/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GlobalPunjab.in\",Global Punjab (720p) [Not 24/7]\n" +
                "https://media.streambrothers.com:1936/8522/8522/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Goa365.in\",Goa365 (720p)\n" +
                "https://asia.mslivestream.net/mslive/f9fe659e19d111baf97526ad526b55b4.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GoodShepherdTV.in\",Good Shepherd TV (720p)\n" +
                "http://162.250.201.58:8032/goodshepherd/web/index.fmp4.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GoodNewsTV.in\",GoodNews TV (720p)\n" +
                "https://bpgdlwwar3ze-hls-live.wmncdn.net/goodnews/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GospelTVIndia.in\",Gospel TV India (720p)\n" +
                "http://live.adostream.com:1935/gospeltv/gospeltv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GosreeVision.in\",Gosree Vision (576p) [Not 24/7]\n" +
                "https://5a1178b42cc03.streamlock.net/8068/8068/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GraceFamilyTV.in\",Grace Family TV (720p)\n" +
                "https://gracefamilytv.livebox.co.in/gracefamilytvhls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GSTV.in\",GSTV (720p) [Not 24/7]\n" +
                "https://1-213-10546-44.b.cdn13.com/388656798579293628302251.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"GulistanNews.in\",Gulistan News (720p)\n" +
                "https://livestream.jktvonline.com/hls/gul.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HamdardTV.ca\",Hamdard TV (1080p)\n" +
                "https://tv.hamdardtv.com/hamdard/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HarvestTV.in\",Harvest TV (720p)\n" +
                "https://7mbd4ogkr3gx-hls-live.wmncdn.net/harvesttvlive1/bbb19eae240ec100af921d511efc86a0.sdp/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HarvestTVKeralam.in\",Harvest TV Keralam (720p)\n" +
                "https://live.wmncdn.net/harvestenglish/d1796a22d24e8696c7d5d0b5c349fdd2.sdp/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HighNews.in\",High News (480p)\n" +
                "https://highmedia.livebox.co.in/HIGHNEWShls/LIVE.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HindiKhabar.in\",Hindi Khabar (720p)\n" +
                "https://server.livelegitpro.in:9899/hindikhabar/hindikhabar/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HopeChannelIndia.in\",Hope Channel India (1080p)\n" +
                "https://videodelivery.net/98334a974d26e45759c6baa8bceabbcf/manifest/video.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HornbillTV.in\",Hornbill TV (720p)\n" +
                "http://172.104.191.216:1935/hornbilltv/hornbilltv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HulchulTVCanada.in\",Hulchul TV (720p) [Not 24/7]\n" +
                "http://cdn12.henico.net:8080/live/jbani/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"HyderTV.in\",Hyder TV (720p)\n" +
                "https://cdn.live247stream.com/hyder/tv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IBC24.in\",IBC 24 (720p)\n" +
                "https://livetv.ibc24.in/livestream.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ImayamTV.in\",Imayam (720p)\n" +
                "https://server1.thewebworld.in:3114/live/iniyantvlive.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ImayamTV.in\",Imayam (480p) [Not 24/7]\n" +
                "https://idvd.multitvsolution.com/idvo/imayamtv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Ind24.in\",IND 24 (360p)\n" +
                "https://gmkd5nkednxo-hls-live.wmncdn.net/ind24tv/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndTVUSA.us\",Ind TV USA (720p)\n" +
                "https://indtv-pull.secure.footprint.net/indtv/stream.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndiaNews.in\",India News (576p) [Not 24/7]\n" +
                "https://livetv.newsx.com/itv/itvnetwork5/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndiaNewsHaryana.in\",India News Haryana (576p)\n" +
                "https://livetv.newsx.com/itv/itvnetwork2/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndiaNewsMadhyaPradeshChhattisgarh.in\",India News Madhya Pradesh/Chhattisgarh (576p)\n" +
                "https://livetv.newsx.com/itv/itvnetwork7/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndiaNewsPunjabHimachal.in\",India News Punjab/Himachal (576p)\n" +
                "https://livetv.newsx.com/itv/itvnetwork8/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndiaNewsRajasthan.in\",India News Rajasthan (576p)\n" +
                "https://livetv.newsx.com/itv/itvnetwork9/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndiaNewsUttarPradesh.in\",India News Uttar Pradesh (576p)\n" +
                "https://livetv.newsx.com/itv/itvnetwork3/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndiaToday.in\",India Today (720p) [Not 24/7]\n" +
                "https://indiatodaylive.akamaized.net/hls/live/2014320/indiatoday/indiatodaylive/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IndywoodTV.in\",Indywood TV (720p)\n" +
                "https://43wrzjnpqoxe-hls-live.wmncdn.net/indywood/indywoodtv/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"INH24x7.in\",INH 24x7 (360p)\n" +
                "https://7epd6o8edk9b-hls-live.wmncdn.net/inh24/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IPlusTV.in\",iPlus TV (1080p) [Not 24/7]\n" +
                "https://live.we2live.in/iplustv/iplustv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IsaiAruvi.in\",Isai Aruvi (576p)\n" +
                "http://103.199.161.254/Content/isaiaruvi/Live/Channel(IsaiAruvi)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"IshwarBhaktiTV.in\",Ishwar Bhakti TV (720p)\n" +
                "https://6n3yow8pl9ok-hls-live.5centscdn.com/ishwartvlive/tv.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"JanamTV.in\",Janam TV (720p)\n" +
                "https://vidcdn.vidgyor.com/janamtv-origin/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"JantaTV.in\",Janta TV (360p) [Not 24/7]\n" +
                "https://live.wmncdn.net/jantatv/live.stream/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"JeevanTV.in\",Jeevan TV (396p)\n" +
                "https://yupplivefragcp5.yuppcdn.net/030523/smil:jeevan.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"JK24x7News.in\",JK 24x7 News (720p)\n" +
                "https://livestream.jktvonline.com/hls/jk.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Jonack.in\",Jonack TV (360p) [Not 24/7]\n" +
                "https://cdn.smartstream.video/smartstream-us/jonakk/jonakk/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KNewsIndia.in\",K News India (1080p)\n" +
                "https://knews.livebox.co.in/youtubehls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KadakHits.in\",Kadak Hits (1080p) [Not 24/7]\n" +
                "https://live.hungama.com/linear/kadak-hits/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KairaliWe.in\",Kairali We (396p)\n" +
                "https://yuppmedtaorire.akamaized.net/v1/master/a0d007312bfd99c47f76b77ae26b1ccdaae76cb1/wetv_nim_https/050522/wetv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KairaliNews.in\",Kairali News\n" +
                "https://ythls.armelin.one/channel/UCnEvxaWfVL91XIYuyQRO5QA.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KalaignarTV.in\",Kalaignar (576p)\n" +
                "http://103.199.161.254/Content/kalaignartv/Live/Channel(KalaignarTV)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KalaignarMurasu.in\",Kalaignar Murasu (576p) [Not 24/7]\n" +
                "https://segment.yuppcdn.net/050522/murasu/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KalaignarSeithigal.in\",Kalaignar Seithikal (576p) [Not 24/7]\n" +
                "http://103.199.160.85/Content/kalaignarseithikal/Live/Channel(KalaignarSeithikal)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SirippoliTV.in\",Kalaignar Sirippoli (576p)\n" +
                "http://103.199.161.254/Content/kalaignarsirippoli/Live/Channel(Kalaignarsirippoli)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KalikaTV.in\",Kalika TV (576p) [Not 24/7]\n" +
                "http://185.105.4.244:1935/livesp/kalika/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KalingaTV.in\",Kalinga TV (864p) [Not 24/7]\n" +
                "https://live.mycast.in/kalingatv/d0dbe915091d400bd8ee7f27f0791303.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KanakNews.in\",Kanak News (720p) [Not 24/7]\n" +
                "https://live.kanaknews.com:4443/live/stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KappaTV.in\",Kappa TV (360p)\n" +
                "https://idvd.multitvsolution.com/idvo/kappatv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KashishNews.in\",Kashish News (720p)\n" +
                "https://kashishnews.livebox.co.in/kashishnewshls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KasthuriTV.in\",Kasthuri (576p)\n" +
                "http://103.199.161.254/Content/kasthuritv/Live/Channel(KasthuriTV)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KaumudyTV.in\",Kaumudy TV (720p)\n" +
                "https://oqgdrkxby4rm-hls-live.5centscdn.com/kaumudytv/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KCLTV.in\",KCL TV (720p)\n" +
                "https://kcltv.livebox.co.in/kclhls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KeralaVision.in\",Kerala Vision (720p)\n" +
                "https://yupplivefragcp3.yuppcdn.net/260423/smil:keralavision.smil/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Keralabhumi.in\",Keralabhumi (576p)\n" +
                "http://103.153.92.157:11037/hls/kbh.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KhabarFast.in\",Khabar Fast (720p) [Not 24/7]\n" +
                "https://live.gbtechnology.in/khabarfast/khabarfast/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KhabrainAbhiTak.in\",Khabrain Abhi Tak (480p) [Not 24/7]\n" +
                "https://vidcdn.vidgyor.com/kat-origin/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"KiteVicters.in\",KITE Victers (Kerala) (720p) [Not 24/7]\n" +
                "https://932y4x26ljv8-hls-live.5centscdn.com/victers/tv.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"LifeTV.in\",Life TV (480p) [Not 24/7]\n" +
                "http://59c3ec70cfde0.streamlock.net/channel_6/channel6/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"LivingIndiaNews.in\",Living India News (360p)\n" +
                "https://livelegitpro.in/hls14/livingindia/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"LokSabhaTV.in\",Lok Sabha TV (1080p)\n" +
                "#EXTVLCOPT:http-referrer=https://webcast.gov.in/\n" +
                "https://playhls.media.nic.in/hls/live/lstv/lstv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MSignMedia.in\",M Sign Media (720p) [Not 24/7]\n" +
                "http://rtmp2.logicwebs.in:1935/msign/msignmedia/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MadhaTV.in\",Madha TV (600p)\n" +
                "https://60e68b19dd194.streamlock.net:55/madhatv/madhatv.stream_HDp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MahaMovie.in\",Maha Movie (576p) [Not 24/7]\n" +
                "http://14.199.164.20:4001/play/a0q7/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MalabarNews.in\",Malabar News (720p) [Not 24/7]\n" +
                "http://cloud.logicwebs.in:1935/live/malabarnews/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MandraTV.in\",Mandra TV\n" +
                "http://192.99.2.28/mandra_hls/mandra.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ManoramaNews.in\",Manorama News (576p)\n" +
                "http://103.199.161.254/Content/manoramanews/Live/Channel(ManoramaNews)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MathrubhumiNews.in\",Mathrubhumi News (576p) [Not 24/7]\n" +
                "http://103.199.161.254/Content/mathrubhuminews/Live/Channel(Mathrubhuminews)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Mayyazhi.in\",Mayyazhi (720p) [Not 24/7]\n" +
                "http://131.153.22.8:1935/MAYYAZHI/live/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MazhavilManorama.in\",Mazhavil Manorama (396p)\n" +
                "https://yuppmedtaorire.akamaized.net/v1/master/a0d007312bfd99c47f76b77ae26b1ccdaae76cb1/mazhavilmanorama_nim_https/050522/mazhavilmanorama/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MercyTV.in\",Mercy TV (1080p)\n" +
                "https://5dd3981940faa.streamlock.net/mercytv/mercytv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MetroTV.in\",Metro TV (576p)\n" +
                "https://2nbyjxw5l53k-hls-live.qezycdn.com/metrotv/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Mh1Music.in\",MH One Music (1080p)\n" +
                "https://mhonepunjabi.in/hls/stream.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Mh1News.in\",MH One News (1080p)\n" +
                "https://mhonenews.in/hls/stream.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MHOneShraddha.in\",MH One Shraddha (1080p)\n" +
                "https://mhoneshaddha.com/hls/stream.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MirrorNow.in\",Mirror Now (720p)\n" +
                "https://pubads.g.doubleclick.net/ssai/event/DXkHhH2QSnma-HnE3QJqlA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MKSix.in\",MK Six (576p) [Not 24/7]\n" +
                "http://103.199.160.85/Content/mktv6/Live/Channel(MKTV6)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MKTunes.in\",MK Tunes (576p) [Not 24/7]\n" +
                "http://103.199.160.85/Content/mktunes/Live/Channel(MKTunes)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MKTV.in\",MK TV (576p) [Not 24/7]\n" +
                "http://103.199.160.85/Content/mktv/Live/Channel(MKTV)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MarutamTV.in\",MNTV (1080p)\n" +
                "https://mntv.livebox.co.in/mntvhls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MarutamMusic.in\",MNTV Music (1080p)\n" +
                "https://mntv.livebox.co.in/musichls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MoonTV.in\",Moon TV (720p)\n" +
                "https://player.mslivestream.net/mslive/e10bb900976df9177b9a080314f26f86.sdp/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MTVBeats.in\",MTV Beats (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/MTV_Beats_HD_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MTV.in\",MTV HD\n" +
                "http://144.217.70.181:9587/hin2/MTVINDIA/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MTV.in\",MTV HD+ (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/MTV_HD_Plus_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MusicIndia.in\",Music India (576p) [Not 24/7]\n" +
                "http://103.199.160.85/Content/musicindia/Live/Channel(MusicIndia)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NambikkaiTV.in\",Nambikkai TV (576p)\n" +
                "https://live.wmncdn.net/nambikkaitv/live.stream/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NamdhariTV.in\",Namdhari (404p) [Not 24/7]\n" +
                "https://namdhari.tv/live/sbs1.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NandighoshaTV.in\",Nandighosha TV (720p)\n" +
                "https://www.nandighoshatvlive.com/hls/stream/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NaxatraNews.in\",Naxatra News (720p)\n" +
                "https://wearelive.livebox.co.in/naxatratvhls/naxatratv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NazranaMusic.in\",Nazrana (720p) [Not 24/7]\n" +
                "https://live.hungama.com/linear/nazrana/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NCV.in\",NCV (720p)\n" +
                "http://131.153.22.8:1935/NCV/ncvstream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NDTV24x7.in\",NDTV 24X7 (480p) [Not 24/7]\n" +
                "https://ndtv24x7elemarchana.akamaized.net/hls/live/2003678/ndtv24x7/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NDTVIndia.in\",NDTV India (480p) [Not 24/7]\n" +
                "https://ndtvindiaelemarchana.akamaized.net/hls/live/2003679/ndtvindia/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NDTVProfit.in\",NDTV Profit [Geo-blocked]\n" +
                "https://ndtv.live-s.cdn.bitgravity.com/cdn-live/_definst_/ndtv/live/ndtv_profit.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NDTVProfit.in\",NDTV Profit (480p)\n" +
                "https://ndtvprofitelemarchana.akamaized.net/hls/live/2003680-b/ndtvprofit/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NDTVProfit.in\",NDTV Profit (480p) [Not 24/7]\n" +
                "https://ndtvprofitelemarchana.akamaized.net/hls/live/2003680/ndtvprofit/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"News1st.in\",News 1st (1080p) [Not 24/7]\n" +
                "https://932y4563djv8-hls-live.qezycdn.com/newsfirst/cb582cc2f3b08e0bbd1c9f3d23541e26.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"News11.in\",News 11 (360p)\n" +
                "https://rtmp.smartstream.video/news11/news11/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"News24.in\",News 24 (720p)\n" +
                "https://vidcdn.vidgyor.com/news24-origin/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NewsLive.in\",News Live (720p)\n" +
                "https://5b48d7e1b4bce.streamlock.net/myapp/newslive/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NewsNation.in\",News Nation (720p)\n" +
                "https://livetv-channels.b-cdn.net/8006/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NickHDPlus.in\",Nick HD+ (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Nick_HD_Plus_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NickJr.in\",Nick Jr India (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Nick_Junior_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NickelodeonSonic.in\",Nickelodeon Sonic Hindi (1080p)\n" +
                "https://prod-sports-north-gm.jiocinema.com/bpk-tv/Sonic_Nickelodeon_voot_MOB/Fallback/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NKTV24x7.in\",NK TV 24x7 (720p)\n" +
                "https://rtmp.smartstream.video/nktvlive/nktvlive/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"NortheastLive.in\",Northeast Live (720p)\n" +
                "https://5b48d7e1b4bce.streamlock.net/myapp/mbrstream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"OneTV.in\",One TV (720p)\n" +
                "http://137.59.86.218:1935/live/onetv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PolimerNews.in\",Polimer News (720p) [Geo-blocked]\n" +
                "https://versewsa.pc.cdn.bitgravity.com/versewsa/live/polimernews.smil/chunklist_b1800000.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PolimerNews.in\",Polimer News (576p) [Not 24/7]\n" +
                "http://14.199.164.20:4001/play/a0lg/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PopPataka.in\",Pop Pataka (480p) [Not 24/7]\n" +
                "https://live.hungama.com/linear/pop-pataka/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PowerTV.in\",Power TV (1080p)\n" +
                "https://liveboxvideoconference.livebox.co.in/PowersmartMediahls/powertvlive.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PrameyaNews7.in\",Pramaya News7 (576p) [Not 24/7]\n" +
                "https://live.mycast.in/encode/ee0c5a36ff5a7083ee044991974ad3ba.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PrathamKhabar24x7.in\",Pratham Khabar 24x7 (576p)\n" +
                "https://livelegitpro.in/hls2/newstime/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PratidinTime.in\",Pratidin Time (720p) [Not 24/7]\n" +
                "https://5b48d7e1b4bce.streamlock.net/pratidintime/pratidintime/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PratidinTime.in\",Pratidin Time (360p) [Not 24/7]\n" +
                "https://rtmp.smartstream.video/pratidintime/pratidintime/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PravasiChannel.in\",Pravasi Channel (1080p)\n" +
                "https://m6gdavepdn93-hls-live.5centscdn.com/pravasi/d0dbe915091d400bd8ee7f27f0791303.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PrudentMedia.in\",Prudent Media (1080p)\n" +
                "https://prudentmcdn.rixcast.com/prudentm.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PTCChakde.in\",PTC Chakde (720p)\n" +
                "https://streaming.ptcplay.com/ptcchakdeINONE/smil:Live.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PTCDholTV.in\",PTC Dhol (720p)\n" +
                "https://streaming.ptcplay.com/ptcdholtvINOne/smil:Live.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PTCMusic.in\",PTC Music (720p)\n" +
                "https://streaming.ptcplay.com/ptcMusicINOne/smil:Live.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PTCNews.in\",PTC News (720p)\n" +
                "https://streaming.ptcplay.com/PTCNEWSINONE/smil:Live.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PTCPunjabi.in\",PTC Punjabi (720p)\n" +
                "https://streaming.ptcplay.com/ptcPunjabiINOne/smil:Live.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PTCPunjabiGold.in\",PTC Punjabi Gold (720p)\n" +
                "https://streaming.ptcplay.com/ptcpunjabiGoldINOne/smil:Live.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PTCSimran.in\",PTC Simran (720p)\n" +
                "https://streaming.ptcplay.com/ptcSimranINOne/smil:Live.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PublicMusic.in\",Public Music (576p) [Not 24/7]\n" +
                "http://103.199.161.254/Content/publicmusic/Live/Channel(PublicMusic)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PublicTV.in\",Public TV (1080p)\n" +
                "https://livestream.knowtable.xyz:5443/show/streams/Publictv_adaptive.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PulariTV.in\",Pulari TV (720p)\n" +
                "https://royalstarindia.co.in/pularitv_hls/pularitv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PunjabiZindabadTV.in\",Punjabi Zindabad (360p) [Not 24/7]\n" +
                "http://stream.pztv.online/pztv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"PuthuyugamTV.in\",Puthuyugam TV (576p) [Not 24/7]\n" +
                "http://103.199.160.85/Content/puthuyugam/Live/Channel(Puthuyugam)/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"RPlus.in\",R Plus (768p)\n" +
                "https://thelegitpro.in/pntv/rplusnews24x7/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"RajNewsMalayalam.in\",Raj News Malayalam\n" +
                "https://rajnewsmalayalam.ylivestream.com/rajnewsmalayalam/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"RDXGoa.in\",RDX Goa (720p)\n" +
                "https://g5nl6xoalpq6-hls-live.5centscdn.com/rdxgoa/d0dbe915091d400bd8ee7f27f0791303.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"RealNewsKerala.in\",Real News Kerala (1080p) [Not 24/7]\n" +
                "https://bk7l298nyx53-hls-live.5centscdn.com/realnews/e7dee419f91aa9e65939d3677fb9c4f5.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"RealTV.in\",Real TV (720p) [Not 24/7]\n" +
                "http://cloud.logicwebs.in:1935/realtv/realtv1/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ReporterTV.in\",Reporter TV (396p)\n" +
                "https://yuppmedtaorire.akamaized.net/v1/master/a0d007312bfd99c47f76b77ae26b1ccdaae76cb1/reporter_nim_https/050522/reporter/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"RepublicBharat.in\",Republic Bharat (360p) [Geo-blocked]\n" +
                "https://republic.pc.cdn.bitgravity.com/live/bharat_hls/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"RepublicTV.in\",Republic TV [Geo-blocked]\n" +
                "https://weblive.republicworld.com/liveorigin/republictv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SADATV.in\",Sada TV (1080p) [Not 24/7]\n" +
                "http://cdn12.henico.net:8080/live/sadatv/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Sadhna.in\",Sadhna (720p)\n" +
                "https://6n3yow8pl9ok-hls-live.5centscdn.com/sadhanalivetv/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SadhnaPlusNews.in\",Sadhna Plus News (720p)\n" +
                "https://6n3yow8pl9ok-hls-live.5centscdn.com/sadhananewstv/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SafariTV.in\",Safari TV (480p) [Not 24/7]\n" +
                "https://j78dp346yq5r-hls-live.5centscdn.com/safari/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SamacharPlus24x7.in\",Samachar Plus (1080p)\n" +
                "https://samacharplus.livebox.co.in/samacharplushls/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SamayKolkata.in\",Samay Kolkata (720p)\n" +
                "https://5dd3981940faa.streamlock.net/kbcration/kbcration/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SanaPlus.in\",Sana Plus (720p) [Not 24/7]\n" +
                "http://media.7starcloud.com:1935/live/sanatv2/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SanaTV.in\",Sana TV (576p) [Not 24/7]\n" +
                "http://hdserver.7starcloud.com:1935/sanatv/sanatv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SansadTV.in\",Sansad TV (1080p) [Not 24/7]\n" +
                "https://hls.media.nic.in/hls/live/lstv/lstv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SansadTV.in\",Sansad TV (Widescreen) (1080p) [Not 24/7]\n" +
                "https://hls.media.nic.in/hls/live/rstv/rstv.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SanskarTV.in\",Sanskar TV (1080p)\n" +
                "https://d26idhjf0y1p2g.cloudfront.net/out/v1/cd66dd25b9774cb29943bab54bbf3e2f/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SanskarUK.in\",Sanskar UK (1080p)\n" +
                "https://d34z4embz0hjf6.cloudfront.net/out/v1/7ac2789ff9a544a49337d1ffc54ce61c/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SanskarUSA.in\",Sanskar USA (1080p)\n" +
                "https://d2netiedy8cz3x.cloudfront.net/out/v1/9bf6fa4ac8d6432cb98da13b121ba3c2/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SanskarWebTV.in\",Sanskar Web TV (1080p)\n" +
                "https://deatfcv3xdvi3.cloudfront.net/out/v1/7a43dd2f64e34ec28da1b4bd6923251a/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SatsangTV.in\",Satsang TV (1080p)\n" +
                "https://d2vfwvjxwtwq1t.cloudfront.net/out/v1/6b24239d5517495b986e7705490c6e65/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SatsangWebTV.in\",Satsang Web TV (1080p)\n" +
                "https://d1ji7e9jbzm5g8.cloudfront.net/out/v1/769f22f64d80442889306b9c4abea63c/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Shalom.in\",Shalom TV (720p) [Not 24/7]\n" +
                "https://api.new.livestream.com/accounts/25038049/events/7483919/live.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ShemarooMarathiBana.in\",Shemaroo Marathi Bana (720p)\n" +
                "https://livetv-channels.b-cdn.net/8042/chunklist0.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ShubhTV.in\",Shubh TV (1080p)\n" +
                "https://d2g1vdc6ozl2o8.cloudfront.net/out/v1/0a0dc7d7911b4fddbb4dfc963fdd4b9e/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Shubhsandesh.in\",Shubhsandesh (720p) [Not 24/7]\n" +
                "https://6284rn2xr7xv-hls-live.wmncdn.net/shubhsandeshtv1/live123.stream/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SongdewTV.in\",Songdew TV (396p)\n" +
                "https://yupplivefragcp3.yuppcdn.net/260423/smil:songdew.smil/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyBBCEarth.in\",Sony BBC Earth HD (1080p)\n" +
                "http://103.81.104.118/hls/stream5.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen5.in\" user-agent=\"stream\",Sony Six HD (1080p)\n" +
                "#EXTVLCOPT:http-user-agent=stream\n" +
                "http://103.81.104.118/hls/stream10.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen1.in\",Sony Sports Ten 1 (1080p)\n" +
                "https://dai.google.com/linear/hls/event/wG75n5U8RrOKiFzaWObXbA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen2.in\",Sony Sports TEN 2 HD (1080p)\n" +
                "https://dai.google.com/linear/hls/event/V9h-iyOxRiGp41ppQScDSQ/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen3.in\",Sony Sports Ten 3 HD (1080p)\n" +
                "https://dai.google.com/linear/hls/event/ltsCG7TBSCSDmyq0rQtvSA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen4.in\",Sony Sports Ten 4 HD (1080p)\n" +
                "https://dai.google.com/linear/hls/event/tNzcW2ZhTVaViggo5ocI-A/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen5.in\",Sony Sports Ten 5 HD (1080p)\n" +
                "https://dai.google.com/linear/hls/event/Sle_TR8rQIuZHWzshEXYjQ/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyKALHindi.us\",Sony Kal (1080p)\n" +
                "https://spt-sonykal-1-us.lg.wurl.tv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyEntertainmentTelevisionAsiaHD.in\",Sony Entertainment Television Asia HD (1080p)\n" +
                "https://dai.google.com/linear/hls/event/dBdwOiGaQvy0TA1zOsjV6w/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySAB.in\",Sony SAB (1080p)\n" +
                "https://dai.google.com/linear/hls/event/CrTivkDESWqwvUj3zFEYEA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyPal.in\",Sony Pal (1080p)\n" +
                "https://dai.google.com/linear/hls/event/dhPrGRwDRvuMQtmlzppzQQ/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyYay.in\",Sony Yay! (1080p)\n" +
                "https://dai.google.com/linear/hls/event/GPY7RqOrSkmKJ8z1GbVNhg/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyMarathi.in\",Sony Marathi (1080p)\n" +
                "https://dai.google.com/linear/hls/event/I2phC6tgTDuJngxw9gJgPw/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyAath.in\",Sony Aath (1080p)\n" +
                "https://dai.google.com/linear/hls/event/j-YEIDwORxubtP_967VcZg/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MaxMiddleEast.in\",Sony MAX HD (1080p)\n" +
                "https://dai.google.com/linear/hls/event/UcjHNJmCQ1WRlGKlZm73QA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"MaxMiddleEast.in\",Sony MAX (1080p)\n" +
                "https://dai.google.com/linear/hls/event/Oc1isQAET3WaNPoABfScmg/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyWah.in\",Sony Wah (1080p)\n" +
                "https://dai.google.com/linear/hls/event/gX5rCBf6Q7-D5AWY-sovzQ/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyMax2.in\",Sony Max 2 (1080p)\n" +
                "https://dai.google.com/linear/hls/event/MdQ5Zy-PSraOccXu8jflCg/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyPix.in\",Sony Pix (1080p)\n" +
                "https://dai.google.com/linear/hls/event/x7rXWd2ERZ2tvyQWPmO1HA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonyBBCEarth.in\",Sony BBC Earth HD (1080p)\n" +
                "https://dai.google.com/linear/hls/event/6bVWYIKGS0CIa-cOpZZJPQ/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen1.in\",Sony Sports Ten 1 (1080p)\n" +
                "https://dai.google.com/linear/hls/event/6WhVNGKTRXyu588zZv1Lkg/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen2.in\",Sony Sports Ten 2 (1080p)\n" +
                "https://dai.google.com/linear/hls/event/LK-ik89MQIi_pWBbg74KNQ/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen3.in\",Sony Sports Ten 3 (1080p)\n" +
                "https://dai.google.com/linear/hls/event/BCOFZq1JQjq12fmaO6lAAA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen4.in\",Sony Sports Ten 4 (1080p)\n" +
                "https://dai.google.com/linear/hls/event/smYybI_JToWaHzwoxSE9qA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SonySportsTen5.in\",Sony Sports Ten 5 (1080p)\n" +
                "https://dai.google.com/linear/hls/event/r-eLp41YTHWTagvQSXFtAQ/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SooriyanTV.ca\",Sooriyan TV\n" +
                "https://cdn.jwplayer.com/videos/1m0nMXhZ-3SDdwqdV.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"\",Star Family (576p) [Not 24/7]\n" +
                "http://c0.cdn.trinity-tv.net/stream/zfmjgma9zn46fa797ez9fgkw7msh9mj4tppspg23gey6mmx5fqiy7ky3jqx4uhgsfsrd8r76si8ykb2anw9442g4qkq5fzpdvwdqf5te24ixu9zrx3aesm9fzt59q5y2s8qwgbqhvf6d3z5bjy3qb2t4.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"StarGold.in\" user-agent=\"stream\",Star Gold HD (1080p)\n" +
                "#EXTVLCOPT:http-user-agent=stream\n" +
                "http://103.81.104.118/hls/stream19.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"StarMovies.in\",Star Movies India (576p) [Not 24/7]\n" +
                "http://c0.cdn.trinity-tv.net/stream/nh9u54a7sfnc2hwzxr2zwykwkqm43bgyyzsm68ybbbnjei8kytwcgs3zm5gnw5c6efa5gr3fadzqe686w8gj2eaehrj89wvujvqza3kez78dtknwbbmnqf79yygmqqg7e9pnc3i3bpywjkiqrwke94yf.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Starnet.in\",Starnet (480p)\n" +
                "https://5a1178b42cc03.streamlock.net/8220/8220/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SteelbirdMusic.in\",Steelbird Music (720p) [Not 24/7]\n" +
                "http://cdn25.live247stream.com/steelbirdmusic/tv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SubhavaarthaTV.in\",Subhavaartha TV (720p)\n" +
                "https://2mk9qae4rwyb-hls-live.wmncdn.net/shubhavartha/live.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SudarshanNews.in\",Sudarshan News (720p)\n" +
                "https://ott.livelegitpro.in/pusa/sudarshantv/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SVBC2.in\",SVBC 2 (1080p)\n" +
                "http://player.mslivestream.net/tamil/ac206e74d75b285755ee4924df87d951.sdp/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SVBC3.in\",SVBC 3 (720p)\n" +
                "https://player.mslivestream.net/svbc/2e628d7e1b65d31254fd7705ff7ee64d.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SVBC4.in\",SVBC 4 (1080p)\n" +
                "https://player.mslivestream.net/mslive/13a2927187b9700ae7ea82d7841d5b68.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SVBC.in\",SVBC Sri Venkateswara Bhakti Channel (1080p)\n" +
                "https://player.mslivestream.net/telugu/5d076e5c3d34cb8bb08e54a4bb7e223e.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"\",SwagJatt (720p)\n" +
                "https://ssh101.bozztv.com/ssh101/swagjatt/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Swantham.in\",Swantham (720p)\n" +
                "http://131.153.22.8:1935/SWANTHAM/live/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SwarajExpressSMBC.in\",Swaraj Express (720p) [Not 24/7]\n" +
                "https://live.wmncdn.net/highnews/swaraj.stream/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TaazaTV.in\",Taaza TV (1080p)\n" +
                "https://stream.playbox.co.in:19360/taaza/taaza.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ThanthiTV.in\",Thanthi TV (396p)\n" +
                "https://segment.yuppcdn.net/110322/thanthi/110322/thanthi_1800/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TimeVisionNews.in\",Time Vision News (720p)\n" +
                "http://rtmp.logichost.in:1935/timevision/timevision/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TimesNow.in\",Times Now [Geo-blocked]\n" +
                "https://pubads.g.doubleclick.net/ssai/event/1mR1QUQ3Tg-VuKfiyjwNuA/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TimesNow.in\",Times Now (480p) [Geo-blocked]\n" +
                "https://timesnow-lh.akamaihd.net/i/TNHD_1@129288/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TimesNowNavbharat.in\",Times Now Navbharat [Geo-blocked]\n" +
                "https://pubads.g.doubleclick.net/ssai/event/nRWQsGoJSzmcErJFgAy67g/master.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"\",Toonz Kids (Indonesian dub) (576p)\n" +
                "http://210.210.155.37/x6bnqe/s/s81/01.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"\",Toonz Kids (Indonesian dub) (360p)\n" +
                "http://210.210.155.37/x6bnqe/s/s81/02.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TotalBhakti.in\",Total Bhakti (1080p)\n" +
                "https://d34z4embz0hjf6.cloudfront.net/out/v1/d55b3323a9f142638f897378f0b526fe/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TribeTV.in\",Tribe TV (720p)\n" +
                "https://server.livelegitpro.in:9899/tribetv/tribetv/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TV9Bangla.in\",TV9 Bangla (720p)\n" +
                "https://dyjmyiv3bp2ez.cloudfront.net/pub-iotv9banaen8yq/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TV9Bharatvarsh.in\",TV9 Bharatvarsh (720p)\n" +
                "https://dyjmyiv3bp2ez.cloudfront.net/pub-iotv9hinjzgtpe/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TV9Gujarati.in\",TV9 Gujarati (720p)\n" +
                "https://dyjmyiv3bp2ez.cloudfront.net/pub-iotv9guj3ki8lu/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TV9Kannada.in\",TV9 Kannada (720p) [Not 24/7]\n" +
                "https://dyjmyiv3bp2ez.cloudfront.net/pub-iotv9kanmo6oiq/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TV9Marathi.in\",TV9 Marathi (720p)\n" +
                "https://dyjmyiv3bp2ez.cloudfront.net/pub-iotv9marlygv8h/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TV9Telugu.in\",TV9 Telugu (720p)\n" +
                "https://dyjmyiv3bp2ez.cloudfront.net/pub-iotv9telcmjhcs/liveabr/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TV84.us\",TV84 (480p)\n" +
                "https://cdn20.liveonlineservices.com/hls/tv84.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TVPunjab.ca\",TV Punjab (720p)\n" +
                "https://932y483pdjv8-hls-live.5centscdn.com/stream/deb10bae362f810630ec3abedcae5894.sdp/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TwentyFourNews.in\",Twenty Four News (396p)\n" +
                "https://segment.yuppcdn.net/110322/channel24/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VaanavilTV.in\",Vaanavil TV (720p)\n" +
                "https://6n3yope4d9ok-hls-live.5centscdn.com/vaanavil/TV.stream/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VasanthTV.in\",Vasanth TV (240p)\n" +
                "https://vasanth.pc.cdn.bitgravity.com/vasanth/secure/live/feed01/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"\",Vathanam TV\n" +
                "http://95.216.167.183:5080/LiveApp/streams/443106610169904881506470.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VBCNews.in\",VBC News (576p)\n" +
                "https://5a1178b42cc03.streamlock.net/8200/8200/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VCV.in\",VCV (720p) [Not 24/7]\n" +
                "https://5a1178b42cc03.streamlock.net/8210/8210/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Velicham.in\",Velicham (360p) [Not 24/7]\n" +
                "https://rtmp.smartstream.video/velichamtv/velichamtv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VenadNews.in\",Venad News (360p)\n" +
                "https://5a1178b42cc03.streamlock.net/8222/8222/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Vision.in\",Vision (1080p)\n" +
                "http://103.85.204.205:1935/VISIONMEDIA/live/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VTVNews.in\",VTV News (720p)\n" +
                "https://5b48d7e1b4bce.streamlock.net/myapp/vtvlive/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VTVNews.in\",VTV News (360p)\n" +
                "https://cdn.smartstream.video/smartstream-us/vtvlive/vtvlive/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VyasChannel.in\",Vyas NIC (576p)\n" +
                "https://playhls.media.nic.in/hls/live/vyas/vyas.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"VyasChannel.in\",Vyas NIC (480p)\n" +
                "#EXTVLCOPT:http-referrer=https://webcast.gov.in/\n" +
                "https://hls.media.nic.in/hls/live/vyas/vyas.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WeOneKerala.in\",We One Kerala (720p) [Geo-blocked]\n" +
                "http://kl.logicwebs.in:1935/sreemedia/sreemedia/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WION.in\",WION (1080p)\n" +
                "https://d7x8z4yuq42qn.cloudfront.net/index_7.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WION.in\",WION (720p)\n" +
                "https://d7x8z4yuq42qn.cloudfront.net/index_1.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WION.in\",Wion (576p)\n" +
                "http://210.210.155.37/uq2663/h/h91/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WION.in\",WION (504p)\n" +
                "https://d7x8z4yuq42qn.cloudfront.net/index_2.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WION.in\",WION (480p)\n" +
                "https://d7x8z4yuq42qn.cloudfront.net/index_5.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WION.in\",WION (360p)\n" +
                "https://d7x8z4yuq42qn.cloudfront.net/index_4.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WION.in\",WION (240p)\n" +
                "https://d7x8z4yuq42qn.cloudfront.net/index_3.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"WION.in\",WION (Adaptive) (1080p)\n" +
                "https://raw.githubusercontent.com/Alstruit/adaptive-streams/alstruit-10_23_in/streams/in/WION.in.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"YoganadamNews.in\",Yoganadam News (720p)\n" +
                "https://yoganadam.cinesoftcdn.com/yoganadam/live/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZainabiaChannel.in\",Zainabia Channel\n" +
                "https://zainabia.livebox.co.in/ZainabiaChannelhls/channel.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Zee24Kalak.in\",Zee 24 Kalak (720p)\n" +
                "https://livetv-channels.b-cdn.net/8077/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeBiharJharkhand.in\",Zee Bihar Jharkhand (720p)\n" +
                "https://d3dxf2v5wg5rcy.cloudfront.net/out/v1/349f643193e347609b16671d8e0bfb4a/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeBusiness.in\",Zee Business (720p)\n" +
                "https://d8gy12azhr71i.cloudfront.net/out/v1/45be109c5b4f44319e882da947377364/index_5.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeBusiness.in\",Zee Business (480p)\n" +
                "https://d8gy12azhr71i.cloudfront.net/out/v1/45be109c5b4f44319e882da947377364/index_3.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeHindustan.in\",Zee Hindustan (720p)\n" +
                "https://d336l5u76wvq7j.cloudfront.net/out/v1/5990b42d2b7b40bc8e581e90afe6ef94/index_4.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeKeralam.in\",Zee Keralam (576p)\n" +
                "https://cflive-esports-hindi.zee5.com/out/v1/c2a01fab468b452d9af1a1aa66644d9d/Zee_Keralam_ME_SD/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeMadhyaPradeshChhattisgarh.in\",Zee Madhya Pradesh Chhattisgarh (720p)\n" +
                "https://dg2v5dko941am.cloudfront.net/out/v1/176d62f6612f47a4b94e78c5bc1bcee6/index_4.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeMarathi.in\",Zee Marathi (720p)\n" +
                "https://d3vy3je10jkn7h.cloudfront.net/out/v1/f610383ba97c47e3b04d2e1593ff8549/index_4.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeNews.in\",Zee News (480p)\n" +
                "https://d233nwklwv8p4.cloudfront.net/out/v1/860cb318d23a4c64b58c9d625281170c/index_3.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeNewsMalayalam.in\",Zee News Malayalam (720p)\n" +
                "https://d1vd9xhq6jlm48.cloudfront.net/out/v1/71fcc57c8fe74c5b938451bc2435a93e/index_4.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeRajasthan.in\",Zee Rajasthan (720p)\n" +
                "https://d3596fuob34tr0.cloudfront.net/out/v1/dc9cd568b52340d9a694877ad4b103a8/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeSalaam.in\",Zee Salaam (720p)\n" +
                "https://d3i8oqsdv88b3m.cloudfront.net/out/v1/08f6360f9104421f90319460c0e03f11/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeThirai.in\",Zee Thirai (576p)\n" +
                "http://line.gofast-tv.me/play/live.php?extension=ts&mac=00:1A:79:6F:5A:5D&play_token=oOPicFfGrH&stream=941587\n" +
                "#EXTINF:-1 tvg-id=\"ZeeUttarPradeshUttarakhand.in\",Zee Uttar Pradesh/Uttarakhand (720p)\n" +
                "https://livetv-channels.b-cdn.net/8076/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Zing.in\",Zing! (576p)\n" +
                "http://183.89.246.119:8881/play/a09a/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Zoom.in\",Zoom (1080p)\n" +
                "http://103.81.104.118/hls/stream8.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"TravelxpNetherlands.in\",Travelxp Netherlands\n" +
                "https://travelxp-travelxp-3-nl.samsung.wurl.tv/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"DDManipur.in\",DD Manipur\n" +
                "https://ddmanipur.org/hls/stream1.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"CNNNews18.in\",CNN News 18\n" +
                "https://n18syndication.akamaized.net/bpk-tv/CNN_News18_NW18_MOB/output01/index.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"9XJhakaas.in\",9x Jhakaas\n" +
                "https://amg01281-9xmediapvtltd-9xjhakaas-samsungin-ci2cs.amagi.tv/playlist/amg01281-9xmediapvtltd-9xjhakaas-samsungin/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"9XTashan.in\",9X Tashan\n" +
                "https://amg01281-9xmediapvtltd-9xtashan-samsungin-xz1sd.amagi.tv/playlist/amg01281-9xmediapvtltd-9xtashan-samsungin/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"9XJalwa.in\",9X Jalwa\n" +
                "https://amg01281-9xmediapvtltd-9xjalwa-samsungin-goszf.amagi.tv/playlist/amg01281-9xmediapvtltd-9xjalwa-samsungin/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Pogo.in\",Pogo\n" +
                "https://amg01448-samsungin-pogo-samsungin-dzccl.amagi.tv/playlist/amg01448-samsungin-pogo-samsungin/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"Mastiii.in\",Mastiii\n" +
                "https://amg01448-samsungin-mastiiin-samsungin-o3n9m.amagi.tv/playlist/amg01448-samsungin-mastiiin-samsungin/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"E24.in\",E 24\n" +
                "https://live-e24.dailyhunt.in/eternowsa/live/amlst:E24_,b256,b512,b1024,b1824,.smil/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"ZeeBusiness.in\",Zee Business\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=zeebusiness\n" +
                "#EXTINF:-1 tvg-id=\"ZeeNews.in\",Zee News\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=zeenews\n" +
                "#EXTINF:-1 tvg-id=\"ShemarooMarathiBana.in\",Shemaroo Marathi Bana\n" +
                "https://shemaroo.codeautoplay.workers.dev/?codeby=nd&channel=shemaroomarathibana\n" +
                "#EXTINF:-1 tvg-id=\"ShemarooTV.in\",Shemaroo TV\n" +
                "https://shemaroo.codeautoplay.workers.dev/?codeby=nd&channel=shemarootv\n" +
                "#EXTINF:-1 tvg-id=\"SunNews.in\",Sun News\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=sunnews\n" +
                "#EXTINF:-1 tvg-id=\"ZeePunjabHaryanaHimachal.in\",Zee Punjab Haryana Himachal\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=zeepunjabharyanahimachalpradesh\n" +
                "#EXTINF:-1 tvg-id=\"ZeeMadhyaPradeshChhattisgarh.in\",Zee Madhya Pradesh Chhattisgarh\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=zeemadhyapradeshchattisgarh\n" +
                "#EXTINF:-1 tvg-id=\"ZeeKannadaNews.in\",Zee Kannada News\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=zeekannada\n" +
                "#EXTINF:-1 tvg-id=\"ZeeTeluguNews.in\",Zee Telugu News\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=zeetelugu\n" +
                "#EXTINF:-1 tvg-id=\"Zee24Taas.in\",Zee 24 Taas\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=zee24taas\n" +
                "#EXTINF:-1 tvg-id=\"ZeeHindustan.in\",Zee Hindustan\n" +
                "https://news.codeautoplay.workers.dev/?codeby=nd&channel=zeebharat\n" +
                "#EXTINF:-1 tvg-id=\"ZeeBiharJharkhand.in\",Zee Bihar Jharkhand (720p)\n" +
                "https://raw.githubusercontent.com/Alstruit/adaptive-streams/alstruit-4_24_in/streams/in/ZeeBiharJharkhand.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"CartoonNetwork.in\",Cartoon Network (1080p)\n" +
                "https://amg01448-samsungin-cartoonnw-samsungin-1y8kz.amagi.tv/playlist/amg01448-samsungin-cartoonnw-samsungin/playlist.m3u8\n" +
                "#EXTINF:-1 tvg-id=\"SunMarathi.in\",Sun Marathi\n" +
                "https://other.codeautoplaycdn1.workers.dev/?codeby=nd&channel=sunmarathi";

        try (BufferedReader bufferedReader = new BufferedReader(new StringReader(text))) {
            String currentLine;
            Channel channel = null;

            while ((currentLine = bufferedReader.readLine()) != null) {
                currentLine = currentLine.replace("\"", "");

                if (currentLine.startsWith(EXT_INF_SP)) {
                    channel = new Channel();
                    String[] parts = currentLine.split(",");
                    if (parts.length > 1) {
                        String[] attributes = parts[0].split(" ");
                        for (String attr : attributes) {
                            if (attr.startsWith(TVG_NAME)) {
                                channel.setChannelName(attr.split("=")[1]);
                            } else if (attr.startsWith(TVG_LOGO)) {
                                channel.setChannelImg(attr.split("=")[1]);
                            } else if (attr.startsWith(GROUP_TITLE)) {
                                channel.setChannelGroup(attr.split("=")[1]);
                            }
                        }
                        channel.setChannelName(parts[1].trim());
                    }
                } else if (currentLine.startsWith(KOD_IP_DROP_TYPE)) {
                    if (channel != null) {
                        channel.setChannelDrmType(currentLine.split(KOD_IP_DROP_TYPE)[1].trim());
                    }
                } else if (currentLine.startsWith(KOD_IP_DROP_KEY)) {
                    if (channel != null) {
                        channel.setChannelDrmKey(currentLine.split(KOD_IP_DROP_KEY)[1].trim());
                    }
                } else if (currentLine.startsWith(HTTP) || currentLine.startsWith(HTTPS)) {
                    if (channel != null) {
                        channel.setChannelUrl(currentLine);
                        channelList.add(channel);
                    }
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading file: " + e.getMessage(), e);
        }

        if (!channelList.isEmpty()) {
            return channelList;
        } else {
            Log.e(TAG, "Error: No channels found in the file");
            return null;
        }
    }
}
