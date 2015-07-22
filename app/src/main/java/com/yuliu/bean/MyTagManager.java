package com.yuliu.bean;

import java.util.ArrayList;

public class MyTagManager {

    private static MyTagManager instance;

    private MyTagManager() {
    }

    public static synchronized MyTagManager getInstance() {
        if (instance == null) {
            instance = new MyTagManager();
        }
        return instance;
    }

    public ArrayList<MyHomeTag> initMyHomeTag() {

        String[] title =
                {
                        "PopularOnYouTube", "Music", "Sports", "Gaming", "Education",
                };

        String[][][] data =
                {
                        {
                                {
                                        "Popular Right Now", "PLrEnWoR732-BHrPp_Pm8_VleD68f9s14-",
                                },
                                {
                                        "Just-Released Music Videos", "PLrEnWoR732-D67iteOI6DPdJH1opjAuJt",
                                },
                                {
                                        "The Daily 'Aww'", "PLrEnWoR732-DN561GnxXKMlocLMc4v4jL",
                                },
                                {
                                        "Stories That'll Restore Your Faith in Humanity", "PLrEnWoR732-AtjHiRPQD7-Xaqa3SaXo-4",
                                },
                                {
                                        "Hot New Trailers", "PLrEnWoR732-CvU2EIng1mKhlXJHvaiAVM",
                                },
                                {
                                        "Catch Up On Late Night", "PLrEnWoR732-CN09YykVof2lxdI3MLOZda",
                                },
                                {
                                        "Today's Funniest Clips", "PLrEnWoR732-AKYdZyzAnuf-MnPiw7rT4Q",
                                },
                                {
                                        "Learn Something New", "PLrEnWoR732-DZV1Jc8bUpVTF_HTPbywpE",
                                },
                                {
                                        "That's a Good Question", "PLrEnWoR732-D6uerjQ8dZiyy9bJID58CK"
                                },
                                {
                                        "Brand-New Tech", "PLrEnWoR732-CV75Y0BCvbVyGDtjoghNEg"
                                }
                        },
                        {
                                {
                                        "Popular Music Videos", "PLFgquLnL59alCl_2TQvOiD5Vgm1hCaGSI"
                                },
                                {
                                        "New Music This Week", "PLFgquLnL59alW3xmYiWRaoz0oM3H17Lth"
                                },
                                {
                                        "Got Moves", "PLFgquLnL59am3gKxgT7Tvw-CMAlT4lQiC"
                                },
                                {
                                        "Latest Music Videos", "PLH6pfBXQXHECUaIU3bu9rjG2L6Uhl5A2q"
                                },
                                {
                                        "Top Pop Music Tracks", "PLDcnymzs18LVXfO_x0Ei0R24qDbVtyy66"
                                },
                                {
                                        "Top Funk Tracks", "PLlAUeZBl7BV6lWENNsb78zZrgyzmOBwrq"
                                },
                                {
                                        "Top Hip Hop Music Tracks", "PLH6pfBXQXHECUaIU3bu9rjG2L6Uhl5A2q"
                                },
                                {
                                        "Music Videos That Make No Sense", "PLFgquLnL59amWnKAZyDmWtEUb-xK4Hx3R"
                                },
                                {
                                        "When Puppets Take Over", "PLFgquLnL59amxzoMDgMC5OJhTmRP9gi4B"
                                },
                                {
                                        "YTMA: Breakout Stars", "PLFgquLnL59an8dvjRnn4_9BuCkXekiTJx"
                                },
                                {
                                        "Veteran Rockers Return", "PLFgquLnL59alK03ZaGA3CL4Pq7CwWwSfB"
                                },
                                {
                                        "Movie Soundtrack Hits '15", "PLFgquLnL59alq_j8zF0tJOohLlHl4gmFl"
                                },
                                {
                                        "YTMA: Meet the 2015 Winners", "PLFgquLnL59al-36Hh4ry6wHmkc6EOkdn9"
                                },
                                {
                                        "Top Classical Music Tracks", "PLVXq77mXV53_3HqhCLGv4mz3oVGYd2Aup"
                                },
                                {
                                        "Top Alternative Rock Tracks", "PL47oRh0-pTosOOeXrM-VgAn8ZdPWi3DSW"
                                },
                                {
                                        "Top Blues Tracks", "PLzauiyXIK7Rj1h23BPvDb3sQwmzHhRuyX"
                                },
                                {
                                        "Top Jazz Tracks", "PLMcThd22goGZoKIj4VAX4GCoYjoCNLiTC"
                                },
                                {
                                        "Top Rock Music Tracks", "PLhd1HyMTk3f5yqcPXjLo8qroWJiMMFBSk"
                                },
                                {
                                        "Top Disco Tracks", "PLtYHnS0mhkb_WVLIVwCErFLnjtJrSozkt"
                                },
                                {
                                        "Top Country Music Tracks", "PLvLX2y1VZ-tEmqtENBW39gdozqFCN_WZc"
                                },
                        },
                        {
                                {
                                        "Latest Sports Videos", "PL8fVUTBmJhHJDAtZwiIOooPRurN0hna - j"
                                },
                                {
                                        "Popular Sports Videos", "PL8fVUTBmJhHJmpP7sLb9JfLtdwCmYX9xC"
                                },
                                {
                                        "Live Now – Sports", "PL8fVUTBmJhHKq0MhIplzljtGhHN2E_jk0"
                                },
                                {
                                        "Upcoming Events – Sports", "PL8fVUTBmJhHKOjs3cOED - MHMO3p46tbNg"
                                },
                                {
                                        "Pacquiao talks to CNN about Mayweather showdown",
                                        "PL8fVUTBmJhHK5QJttd - A62OqBzmCjRaPh"
                                },
                                {
                                        "Must Watch Plays", "PL8fVUTBmJhHJlT40aNl_cX4qcrVqoC5eg"
                                },
                                {
                                        "It's Tricky", "PL8fVUTBmJhHL3aBWZKcj2 - yZl33Dlgg8b"
                                },
                                {
                                        "Best All-Time NBA Dunks", "PL8fVUTBmJhHJA50O_3XmuTfLYcXlOUQBk"
                                },
                                {
                                        "Latest Videos – Climbing", "PLup_xg3zfNI - NnY_xGM2gwfy46i88DGYt"
                                },
                                {
                                        "Latest Videos – Drifting", "PLYQWOV6gNS3EUJMDKkhPnwuLYUOhnwp - R"
                                },
                                {
                                        "Latest Videos – Fishing", "PL2B - PWFn - iOYg_zxsr4v62rQ4aaapT4P6"
                                },
                                {
                                        "Latest Videos – Mixed martial arts", "PLSI48sx264bidBZvWn_RN_XOMiSIoo6en"
                                },
                                {
                                        "Latest Videos – Bicycle", "PLWL89d_alIhEHl - AWL0D5UZfEkzlzkKTG"
                                },
                                {
                                        "Latest Videos – Skateboarding", "PLE1H5rdNP - jMfPlnyuk2W7DgogGcpQi7h"
                                },

                        },
                        {

                                {
                                        "Popular Gaming Videos", "PLiCvVJzBupKnKoAJR3T8NxXwA5mPeBD8W"
                                },
                                {
                                        "Live Now – Gaming", "PLiCvVJzBupKmEehQ3hnNbbfBjLUyvGlqx-EZn_GvAqfehHIuqrVI"
                                },
                                {
                                        "Upcoming Events – Gaming", "PLiCvVJzBupKmhv-EZn_GvAqfehHIuqrVI"
                                },
                                {
                                        "Popular Minecraft Videos", "PLZKuf2oBY67hSKfuuorXzyin-JTPOKwlC"
                                },
                                {
                                        "Popular Grand Theft Auto V Videos", "PLr8KIbGK44Lv8A7O6MJSadw_PhdlI4ttR"
                                },
                                {
                                        "Popular Five Nights at Freddy's 2 Videos", "PL1UfJk8c4TmOsYpnWY_92DEMKXoM-wd3O"
                                },
                                {
                                        "Popular Five Nights at Freddy's Videos", "PLfKYDLgOQjN2HqDt-togIiKeNtUEB9Jkg"
                                },
                                {
                                        "Gaming Review Roundup", "PLiCvVJzBupKnL2LpxuzZ9Tuj3aRALMpMn"
                                },
                                {
                                        "Spotlight On: Dying Light", "PLiCvVJzBupKmjHo_YoWAHSLmIBYEzNUSI"
                                },

                        },
                        {

                                {
                                        "Featured Videos", "PLN_VEYjh8gCByLq9iBB_yP4Qyhh95DMyH"
                                },
                                {
                                        "Education", "PL4We4XGulWzBjyRnqq0-j73pxnzQzxbhi"
                                },
                                {
                                        "Medicine", "PL0mifQXDgbZy8-UR936WkJcALVm2qiVq3"
                                },
                                {
                                        "Lifelong Learning", "PLvB21scu1J6qkEvzXxVzJMjH027foJddM"
                                },
                                {
                                        "University", "PLCBvVr9JTXro9Uv8LPXyEFCh7Y4X-Kfqm"
                                },
                                {
                                        "Science", "PL-h5J2udAckkf5_rpqhqMbINJgiKi6xxW"
                                },
                                {
                                        "Business", "PLV0wFx-KcgtNAp20fuANGO_LoKMYoXmLE"
                                },
                                {
                                        "Humanities", "PL7hp3oXD4aCwpdka-grIL6qJPiPGU3Hmz"
                                },
                                {
                                        "Primary & Secondary Education", "PLgM5FyJb22zyL68BvEHF6Iq4famgsIagf"
                                },
                                {
                                        "Social Sciences", "PLIVliAzkSWR8dD-K5gXOYJAHhxSq5R7PN"
                                },
                                {
                                        "Mathematics", "PLFFTmCStUUDZaUUcQI77cVmhMXYFG8dOI"
                                },
                                {
                                        "History", "PL4OyLLLJcKZrd050cGmrQUEz2w31zX9mZ"
                                },
                                {
                                        "Arts", "PL1rLuuHwph50HEFtoJv9bstgCfT0BcEk3"
                                },
                                {
                                        "Law", "PLl0NOzDB1mkZgb-pYi-ZXfolx1QgDe4QG"
                                },
                                {
                                        "Engineering", "PL_i5eEbCqriAAZQF39Y_ZE_8L4KRIxel0"
                                },
                                {
                                        "Languages", "PLwp__PikwafQFIh31ew_xiaPGOyfyVhSm"
                                },
                        },

                };

        ArrayList<MyHomeTag> mTags = new ArrayList<MyHomeTag>();

        for (int i = 0; i < data.length; i++) {
            ArrayList<Item> mItems = new ArrayList<Item>();
            for (int j = 0; j < data[i].length; j++) {
                Item item = new Item(data[i][j][0], data[i][j][1]);
                mItems.add(item);
            }

            MyHomeTag mHomeTag = new MyHomeTag();
            mHomeTag.title = title[i];
            mHomeTag.mItems = mItems;
            mTags.add(mHomeTag);
        }

        return mTags;

    }
    // 1.#PopularOnYouTube UCF0pVplsI8R5kcAqgtoRqoA
    // Popular Right Now
    // https://www.youtube.com/playlist?list=PLrEnWoR732-BHrPp_Pm8_VleD68f9s14-
    // Just-Released Music Videos
    // https://www.youtube.com/playlist?list=PLrEnWoR732-D67iteOI6DPdJH1opjAuJt
    // The Daily 'Aww'
    // https://www.youtube.com/playlist?list=PLrEnWoR732-DN561GnxXKMlocLMc4v4jL
    // Stories That'll Restore Your Faith in Humanity
    // https://www.youtube.com/playlist?list=PLrEnWoR732-AtjHiRPQD7-Xaqa3SaXo-4
    // Hot New Trailers
    // https://www.youtube.com/playlist?list=PLrEnWoR732-CvU2EIng1mKhlXJHvaiAVM
    // Catch Up On Late Night
    // https://www.youtube.com/playlist?list=PLrEnWoR732-CN09YykVof2lxdI3MLOZda
    // Today's Funniest Clips
    // https://www.youtube.com/playlist?list=PLrEnWoR732-AKYdZyzAnuf-MnPiw7rT4Q
    // Learn Something New
    // https://www.youtube.com/playlist?list=PLrEnWoR732-DZV1Jc8bUpVTF_HTPbywpE
    // That's a Good Question
    // https://www.youtube.com/playlist?list=PLrEnWoR732-D6uerjQ8dZiyy9bJID58CK
    // Brand-New Tech
    // https://www.youtube.com/playlist?list=PLrEnWoR732-CV75Y0BCvbVyGDtjoghNEg
    //
    //
    //
    // 2.#Music
    // Popular Music Videos
    // https://www.youtube.com/playlist?list=PLFgquLnL59alCl_2TQvOiD5Vgm1hCaGSI
    // Latest Music Videos
    // https://www.youtube.com/playlist?list=PLH6pfBXQXHECUaIU3bu9rjG2L6Uhl5A2q
    // New Music This Week
    // https://www.youtube.com/playlist?list=PLFgquLnL59alW3xmYiWRaoz0oM3H17Lth
    // Got Moves
    // https://www.youtube.com/playlist?list=PLFgquLnL59am3gKxgT7Tvw-CMAlT4lQiC
    // Top Pop Music Tracks
    // https://www.youtube.com/playlist?list=PLDcnymzs18LVXfO_x0Ei0R24qDbVtyy66
    // Top Funk Tracks
    // https://www.youtube.com/playlist?list=PLlAUeZBl7BV6lWENNsb78zZrgyzmOBwrq
    // Top Hip Hop Music Tracks
    // https://www.youtube.com/playlist?list=PLH6pfBXQXHECUaIU3bu9rjG2L6Uhl5A2q
    // Music Videos That Make No Sense
    // https://www.youtube.com/playlist?list=PLFgquLnL59amWnKAZyDmWtEUb-xK4Hx3R
    // When Puppets Take Over
    // https://www.youtube.com/playlist?list=PLFgquLnL59amxzoMDgMC5OJhTmRP9gi4B
    // Movie Soundtrack Hits '15
    // https://www.youtube.com/playlist?list=PLFgquLnL59alq_j8zF0tJOohLlHl4gmFl
    // YTMA: Meet the 2015 Winners
    // https://www.youtube.com/playlist?list=PLFgquLnL59al-36Hh4ry6wHmkc6EOkdn9
    // Top Classical Music Tracks
    // https://www.youtube.com/playlist?list=PLVXq77mXV53_3HqhCLGv4mz3oVGYd2Aup
    // Top Alternative Rock Tracks
    // https://www.youtube.com/playlist?list=PL47oRh0-pTosOOeXrM-VgAn8ZdPWi3DSW
    // Top Blues Tracks
    // https://www.youtube.com/playlist?list=PLzauiyXIK7Rj1h23BPvDb3sQwmzHhRuyX
    // Top Jazz Tracks
    // https://www.youtube.com/playlist?list=PLMcThd22goGZoKIj4VAX4GCoYjoCNLiTC
    // Top Rock Music Tracks
    // https://www.youtube.com/playlist?list=PLhd1HyMTk3f5yqcPXjLo8qroWJiMMFBSk
    // Top Disco Tracks
    // https://www.youtube.com/playlist?list=PLtYHnS0mhkb_WVLIVwCErFLnjtJrSozkt
    // Top Country Music Tracks
    // https://www.youtube.com/playlist?list=PLvLX2y1VZ-tEmqtENBW39gdozqFCN_WZc
    //
    //
    // Top Music Videos by Genre
    // Celebrities Covering Celebrities
    // YouTube EDM 15: Emerging & Top Songs
    // Veteran Rockers Return
    // Hitting the Gym Mixes
    // Working Overtime
    // Music For Every Mood
    //
    // Top Alternative Rock Tracks
    //
    //
    // 3.#Sports
    // Latest Sports Videos
    // https://www.youtube.com/playlist?list=PL8fVUTBmJhHJDAtZwiIOooPRurN0hna-j
    // Popular Sports Videos
    // https://www.youtube.com/playlist?list=PL8fVUTBmJhHJmpP7sLb9JfLtdwCmYX9xC
    // Live Now – Sports
    // https://www.youtube.com/playlist?list=PL8fVUTBmJhHKq0MhIplzljtGhHN2E_jk0
    // Upcoming Events – Sports
    // https://www.youtube.com/playlist?list=PL8fVUTBmJhHKOjs3cOED-MHMO3p46tbNg
    // Pacquiao talks to CNN about Mayweather showdown
    // https://www.youtube.com/playlist?list=PL8fVUTBmJhHK5QJttd-A62OqBzmCjRaPh
    // Must Watch Plays
    // https://www.youtube.com/playlist?list=PL8fVUTBmJhHJlT40aNl_cX4qcrVqoC5eg
    // It's Tricky
    // https://www.youtube.com/playlist?list=PL8fVUTBmJhHL3aBWZKcj2-yZl33Dlgg8b
    // Best All-Time NBA Dunks
    // https://www.youtube.com/playlist?list=PL8fVUTBmJhHJA50O_3XmuTfLYcXlOUQBk
    //
    // Latest Videos – Climbing
    // https://www.youtube.com/playlist?list=PLup_xg3zfNI-NnY_xGM2gwfy46i88DGYt
    // Latest Videos – Drifting
    // https://www.youtube.com/playlist?list=PLYQWOV6gNS3EUJMDKkhPnwuLYUOhnwp-R
    // Latest Videos – Fishing
    // https://www.youtube.com/playlist?list=PL2B-PWFn-iOYg_zxsr4v62rQ4aaapT4P6
    // Latest Videos – Mixed martial arts
    // https://www.youtube.com/playlist?list=PLSI48sx264bidBZvWn_RN_XOMiSIoo6en
    // Latest Videos – Bicycle
    // https://www.youtube.com/playlist?list=PLWL89d_alIhEHl-AWL0D5UZfEkzlzkKTG
    // Latest Videos – Skateboarding
    // https://www.youtube.com/playlist?list=PLE1H5rdNP-jMfPlnyuk2W7DgogGcpQi7h
    //
    //
    // 4.#Gaming
    // Popular Gaming Videos
    // https://www.youtube.com/playlist?list=PLiCvVJzBupKnKoAJR3T8NxXwA5mPeBD8W
    // Upcoming Events – Gaming
    // https://www.youtube.com/playlist?list=PLiCvVJzBupKmhv-EZn_GvAqfehHIuqrVI
    // Popular Minecraft Videos
    // https://www.youtube.com/playlist?list=PLZKuf2oBY67hSKfuuorXzyin-JTPOKwlC
    // Popular Grand Theft Auto V Videos
    // https://www.youtube.com/playlist?list=PLr8KIbGK44Lv8A7O6MJSadw_PhdlI4ttR
    // Popular Five Nights at Freddy's 2 Videos
    // https://www.youtube.com/playlist?list=PL1UfJk8c4TmOsYpnWY_92DEMKXoM-wd3O
    // Popular Five Nights at Freddy's Videos
    // https://www.youtube.com/playlist?list=PLfKYDLgOQjN2HqDt-togIiKeNtUEB9Jkg
    // Gaming Review Roundup
    // https://www.youtube.com/playlist?list=PLiCvVJzBupKnL2LpxuzZ9Tuj3aRALMpMn
    // Spotlight On: Dying Light
    // https://www.youtube.com/playlist?list=PLiCvVJzBupKmjHo_YoWAHSLmIBYEzNUSI
    //
    //
    // 5.#Education
    // Featured Videos
    // https://www.youtube.com/playlist?list=PLN_VEYjh8gCByLq9iBB_yP4Qyhh95DMyH
    // Education
    // https://www.youtube.com/playlist?list=PL4We4XGulWzBjyRnqq0-j73pxnzQzxbhi
    // Medicine
    // https://www.youtube.com/playlist?list=PL0mifQXDgbZy8-UR936WkJcALVm2qiVq3
    // Lifelong Learning
    // https://www.youtube.com/playlist?list=PLvB21scu1J6qkEvzXxVzJMjH027foJddM
    // University
    // https://www.youtube.com/playlist?list=PLCBvVr9JTXro9Uv8LPXyEFCh7Y4X-Kfqm
    // Science
    // https://www.youtube.com/playlist?list=PL-h5J2udAckkf5_rpqhqMbINJgiKi6xxW
    // Business
    // https://www.youtube.com/playlist?list=PLV0wFx-KcgtNAp20fuANGO_LoKMYoXmLE
    // Humanities
    // https://www.youtube.com/playlist?list=PL7hp3oXD4aCwpdka-grIL6qJPiPGU3Hmz
    // Primary & Secondary Education
    // https://www.youtube.com/playlist?list=PLgM5FyJb22zyL68BvEHF6Iq4famgsIagf
    // Social Sciences
    // https://www.youtube.com/playlist?list=PLIVliAzkSWR8dD-K5gXOYJAHhxSq5R7PN
    // Mathematics
    // https://www.youtube.com/playlist?list=PLFFTmCStUUDZaUUcQI77cVmhMXYFG8dOI
    // History
    // https://www.youtube.com/playlist?list=PL4OyLLLJcKZrd050cGmrQUEz2w31zX9mZ
    // Arts
    // https://www.youtube.com/playlist?list=PL1rLuuHwph50HEFtoJv9bstgCfT0BcEk3
    // Law
    // https://www.youtube.com/playlist?list=PLl0NOzDB1mkZgb-pYi-ZXfolx1QgDe4QG
    // Engineering
    // https://www.youtube.com/playlist?list=PL_i5eEbCqriAAZQF39Y_ZE_8L4KRIxel0
    // Languages
    // https://www.youtube.com/playlist?list=PLwp__PikwafQFIh31ew_xiaPGOyfyVhSm

}
