package com.atasoyh.lastfmartistfinder;


import com.atasoyh.lastfmartistfinder.di.ServiceModule;
import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.model.response.GetArtistInfoResponse;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;
import com.google.gson.Gson;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableJust;


/**
 * Created by atasoyh on 11/07/2017.
 */
@Singleton
@Module
public class TestServiceModule {
    private String mockSearch = "{\"results\":{\"opensearch:Query\":{\"#text\":\"\",\"role\":\"request\",\"searchTerms\":\"tarkan\",\"startPage\":\"1\"},\"opensearch:totalResults\":\"25\",\"opensearch:startIndex\":\"0\",\"opensearch:itemsPerPage\":\"10\",\"artistmatches\":{\"artist\":[{\"name\":\"Tarkan\",\"listeners\":\"180252\",\"mbid\":\"4ec2451d-ed0c-4273-b683-4c1312df25fd\",\"url\":\"https://www.last.fm/music/Tarkan\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/272288aded964783287ef0277379a28d.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/272288aded964783287ef0277379a28d.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/272288aded964783287ef0277379a28d.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/272288aded964783287ef0277379a28d.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/272288aded964783287ef0277379a28d.png\",\"size\":\"mega\"}]},{\"name\":\"Tarakany!\",\"listeners\":\"4405\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarakany%21\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/129a2c971aa842de9948831cc231e0ca.png\",\"size\":\"mega\"}]},{\"name\":\"DJ Tarkan\",\"listeners\":\"4652\",\"mbid\":\"a6a70a9c-a17a-46cd-8a12-ba3fe69f1036\",\"url\":\"https://www.last.fm/music/DJ+Tarkan\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/417b8ec572084b3b8fe83fad82ed34f4.png\",\"size\":\"mega\"}]},{\"name\":\"Tárkány Művek\",\"listeners\":\"284\",\"mbid\":\"e35ae7bb-e482-4240-8d1c-602f7aa15907\",\"url\":\"https://www.last.fm/music/T%C3%A1rk%C3%A1ny+M%C5%B1vek\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/9aa8c39eecbe4e2a888c7de518437f9c.png\",\"size\":\"mega\"}]},{\"name\":\"Tarkan - WwW.PrensesBoard.De.tt\",\"listeners\":\"747\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarkan+-+WwW.PrensesBoard.De.tt\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/2bb6cdde73f34a77850656382bc77b24.png\",\"size\":\"mega\"}]},{\"name\":\"Tárkány-Müvek\",\"listeners\":\"205\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/T%C3%A1rk%C3%A1ny-M%C3%BCvek\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/0fa1f7bdd24246d6ac2cf7fa5a1bd7c2.png\",\"size\":\"mega\"}]},{\"name\":\"Dj Tarkan At Frisky Radio\",\"listeners\":\"338\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Dj+Tarkan+At+Frisky+Radio\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"Tarakani\",\"listeners\":\"209\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarakani\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"Tarkan Tevetoglu\",\"listeners\":\"409\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Tarkan+Tevetoglu\",\"streamable\":\"0\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"},{\"#text\":\"\",\"size\":\"mega\"}]},{\"name\":\"DJ Tarkan & Dammex\",\"listeners\":\"310\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/DJ+Tarkan+&+Dammex\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/26408aae39eb4eeb9d33d88071a034fc.png\",\"size\":\"mega\"}]}]},\"@attr\":{\"for\":\"tarkan\"}}}";
    private String mockArtist = "{\"artist\":{\"name\":\"Tarkan\",\"mbid\":\"4ec2451d-ed0c-4273-b683-4c1312df25fd\",\"url\":\"https://www.last.fm/music/Tarkan\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/272288aded964783287ef0277379a28d.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/272288aded964783287ef0277379a28d.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/272288aded964783287ef0277379a28d.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/272288aded964783287ef0277379a28d.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/272288aded964783287ef0277379a28d.png\",\"size\":\"mega\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/arQ/272288aded964783287ef0277379a28d.png\",\"size\":\"\"}],\"streamable\":\"0\",\"ontour\":\"0\",\"stats\":{\"listeners\":\"180252\",\"playcount\":\"2967303\"},\"similar\":{\"artist\":[{\"name\":\"Mustafa Sandal\",\"url\":\"https://www.last.fm/music/Mustafa+Sandal\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/43d0eca8f3de49d5aa7394177c4f86de.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/43d0eca8f3de49d5aa7394177c4f86de.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/43d0eca8f3de49d5aa7394177c4f86de.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/43d0eca8f3de49d5aa7394177c4f86de.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/43d0eca8f3de49d5aa7394177c4f86de.png\",\"size\":\"mega\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/arQ/43d0eca8f3de49d5aa7394177c4f86de.png\",\"size\":\"\"}]},{\"name\":\"Kenan Doğulu\",\"url\":\"https://www.last.fm/music/Kenan+Do%C4%9Fulu\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/3e4a52cb884b4c8586d269d5a15c59f7.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/3e4a52cb884b4c8586d269d5a15c59f7.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/3e4a52cb884b4c8586d269d5a15c59f7.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/3e4a52cb884b4c8586d269d5a15c59f7.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/3e4a52cb884b4c8586d269d5a15c59f7.png\",\"size\":\"mega\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/arQ/3e4a52cb884b4c8586d269d5a15c59f7.png\",\"size\":\"\"}]},{\"name\":\"Murat Boz\",\"url\":\"https://www.last.fm/music/Murat+Boz\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/355293e1a506829e6e6eb61ccbe9a527.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/355293e1a506829e6e6eb61ccbe9a527.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/355293e1a506829e6e6eb61ccbe9a527.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/355293e1a506829e6e6eb61ccbe9a527.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/355293e1a506829e6e6eb61ccbe9a527.png\",\"size\":\"mega\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/arQ/355293e1a506829e6e6eb61ccbe9a527.png\",\"size\":\"\"}]},{\"name\":\"Sertab Erener\",\"url\":\"https://www.last.fm/music/Sertab+Erener\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/f7cce6fa32264f3aa66f47da67a40f32.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/f7cce6fa32264f3aa66f47da67a40f32.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/f7cce6fa32264f3aa66f47da67a40f32.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/f7cce6fa32264f3aa66f47da67a40f32.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/f7cce6fa32264f3aa66f47da67a40f32.png\",\"size\":\"mega\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/arQ/f7cce6fa32264f3aa66f47da67a40f32.png\",\"size\":\"\"}]},{\"name\":\"Hande Yener\",\"url\":\"https://www.last.fm/music/Hande+Yener\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/d894ad9df88311203248c913d65d5420.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/d894ad9df88311203248c913d65d5420.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/d894ad9df88311203248c913d65d5420.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/d894ad9df88311203248c913d65d5420.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/d894ad9df88311203248c913d65d5420.png\",\"size\":\"mega\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/arQ/d894ad9df88311203248c913d65d5420.png\",\"size\":\"\"}]}]},\"tags\":{\"tag\":[{\"name\":\"turkish\",\"url\":\"https://www.last.fm/tag/turkish\"},{\"name\":\"pop\",\"url\":\"https://www.last.fm/tag/pop\"},{\"name\":\"Turkish Pop\",\"url\":\"https://www.last.fm/tag/Turkish+Pop\"},{\"name\":\"dance\",\"url\":\"https://www.last.fm/tag/dance\"},{\"name\":\"tarkan\",\"url\":\"https://www.last.fm/tag/tarkan\"}]},\"bio\":{\"links\":{\"link\":{\"#text\":\"\",\"rel\":\"original\",\"href\":\"https://last.fm/music/Tarkan/+wiki\"}},\"published\":\"13 Feb 2006, 00:14\",\"summary\":\"Tarkan Tevetoğlu (born October 17, 1972 in Alzey, West Germany), popularly known as Tarkan, is a successful World Music award-winning pop music singer in Turkey. He has released several platinum-selling albums during his career, with an estimated 15 million albums sold, and is also involved in producing music through his own music company HITT Music, which he established in 1997. Noted for his live stage performances, the Washington Post has compared Tarkan's effect on Turkey as analogous to Elvis in America circa 1957 <a href=\\\"https://www.last.fm/music/Tarkan\\\">Read more on Last.fm</a>\",\"content\":\"Tarkan Tevetoğlu (born October 17, 1972 in Alzey, West Germany), popularly known as Tarkan, is a successful World Music award-winning pop music singer in Turkey. He has released several platinum-selling albums during his career, with an estimated 15 million albums sold, and is also involved in producing music through his own music company HITT Music, which he established in 1997. Noted for his live stage performances, the Washington Post has compared Tarkan's effect on Turkey as analogous to Elvis in America circa 1957, while co-founder of Atlantic Records Ahmet Ertegün described him as one of the best live performers he had ever seen.\\n\\nTarkan is one of a few European singers that have managed to span chart success over three continents without singing in English. Often cited as the Turkish Prince of Pop, the German born singer has charted in Russia, Europe and in the Americas with the song \\\"Şımarık\\\" (Kiss Kiss). As a result of its widespread success, Tarkan was listed by Rhapsody as a key artist in the history of European pop music, with his signature song as a keystone track that moved the genre forward.\\n\\nImmediately recognisable with Tarkan's kisses on the track, \\\"Şımarık\\\" was reincarnated as \\\"Kiss Kiss\\\" by Holly Valance after the composer Sezen Aksu sold the music rights, and has been covered by various artists across the world. \\n\\nLONG BIOGRAPHY:\\nTarkan is an entertainer who has broken the mould of the meaning of a pop star and set the standard for a new breed of artist. He is a force that has managed to overcome countless barriers, simply because he sees none.\\n\\nBorn in Alzey, Germany on 10-17-1972, Tarkan is the fifth child of six siblings. Tarkan was reared by his loving mother who noticed his talents and encouraged his artistic ability. Coming from humble beginnings, music was a form of release from his realities. Seeing how music moved their child’s very being, was reason enough for Tarkan’s family to encourage his journey into the world of music. He began on his quest by taking lessons in Turkey in order to study the craft of traditional Turkish song. Studying at the Turkish classical musical institution, Tarkan was surrounded by some of Turkey’s most respectable songwriters & musicians. It was at this very young and impressionable age that Tarkan absorbed the essence of music.\\n\\nAfter graduating from high school, Tarkan had a string of jobs that led him nowhere in the music arena. Experiencing this disappointment, Tarkan was planning on moving to Germany and leaving his dreams of pursuing music and stardom behind. However, prior to his departure, destiny had different plans in store for him as Tarkan met producer Mehmet Sogutoglu. Mehmet eventually signed Tarkan to his record label, Istanbul Plak. This resulted in the release of Tarkan’s debut album Yine Sensiz in 1993 in Turkey. The first single “Kil Oldum Abi” quickly struck a chord with the public, garnering airplay at nightclubs & eventually hitting Turkish radio’s Top 20 charts. The songs “Cok Ararsin Beni” and “Vazgecemem” perpetuated the album’s success. These songs’ airplay was boosted by music videos in which Tarkan, though innovative and unique, maintained a loyalty to his roots & culture, as he was seen donning a necklace with the crescent & star, a Turkish symbol of national pride. Tarkan’s look was one that was never before seen, with his boyish charm and searing eyes, he managed to win the hearts of the youth. He ultimately sold over 700,000 units, taking his place among Istanbul’s prominent artists.\\n\\nTarkan’s sophomore album entitled Aacayipsin was released in 1995. The title track, a collaboration with Sezen Aksu (Turkey’s acclaimed composer/producer/lyricist/performer) became the singer’s first number one single. In 1995, Tarkan held 74 live concerts throughout Turkey & Europe. A New York Palladium concert was broadcast live in Turkey nationwide, to rave reviews. The album’s sales jumped to over 2.5 million units, more albums sold than any other artist that year, cementing Tarkan as a force to be reckoned with in the world of Turkish music.\\n\\nFollowing his successes throughout Turkey, Tarkan chose to leave Turkey to collect his thoughts & learn English, relocating to NY. Prior to his departure, as fate would have it, Tarkan met Atlantic Records founder & executive Ahmet Ertegun, who decided to take the talented musician under his wing & consequently became a guiding force in Tarkan’s life, silently grooming him for the world’s stage.\\n\\nTarkan’s third album, Olurum Sana, was released in July 1997. The single “Simarik”, known as “Kiss Kiss” internationally, was written by Turkey’s legendary Sezen Aksu. The powerhouse song exploded onto the international market, topping the Billboard Charts Hot 100 lists worldwide garnering highest marks, resulting in close to 4 million units sold. “Simarik” was translated into many different languages in order to accommodate the international demand. Furthermore, the music video’s appeal resulted in steady rotation on international music markets including MTV & MCM throughout Europe, as well as profiles on international news outlets including CNN. Tarkan progressed by embarking on a sold out stadium tour nationwide. Following his appearances in Turkey, Tarkan made appearances in over 17 cities in Europe, with additional stops in South America. His entrancing vocals, dynamic dance, and spectacularly staged shows appealed to the worldwide demographic & Tarkan was received everywhere with open ears, arms, & hearts. In 1998, PolyGram France licensed the album, re-releasing it internationally. As a result, the album was certified platinum in Mexico, & hit gold in France, Holland, Germany, Belgium, Luxembourg, Sweden, & Columbia.\\n\\n1999 continued with even more tour dates, including appearances in Germany, France, England, Belgium, Holland, Denmark, Austria, Portugal, Morocco, Australia, & Tunisia until the horrific 1999 earthquake hit Turkey. The devastating effects on its people led Tarkan to hold a moving performance in honor of the victims of the 1999 earthquake, where thousands of Turkish citizens were lost & perhaps even more so were left homeless. This aid concert held in Istanbul was a highlight for the people of Turkey and helped the people of Istanbul regain hope and find the determination to rebuild their lives. Tarkan then continued his activism & philanthropy by enlisting in Turkey’s Military Service, showing loyalty to his country and his people.\\n\\nControversy always managed to follow Tarkan throughout his career. Whether his interests, political views, or personal relationships were in question, he has never ceased to be an integral topic of public intrigue. Tarkan’s quest for self-realization was played out publicly due to his ever-present persona. However, through it all, Tarkan has managed to weather the storm by staying true to his heart, to his music, & to his fans.\\n\\nKarma was released in August 2001 to a hungry audience, which saw a side of Tarkan never before publicly exposed. Karma was a touching, introspective, and deeply personal work, which shined a light on all of the experiences, Tarkan had endured throughout the recent years of pandemonium. Fans witnessed an artist, who in the wake of the new album was a more spiritually centered and private man. “Kuzu Kuzu”, the first single off the album, was released in May 2001 & held the # 1 position for three months. Karma also was also a ravishing success in Russia and a surprising change in the landscape of the Russian music market. In spite of economic & political crisis at the time in Turkey, the album “Karma” sold over 2.5 million units.\\n\\nFollowing this success, Tarkan was asked to compose & perform the official song of the Turkish National Soccer Team (third place medalist in the World Cup). Furthermore, music from the album Karma was also used for documentary films produced by the Turkish Ministry of Tourism, which also rewarded him for his contributions to Turkey & for the poignant representation of his country, through his music and throughout the world.\\n\\nDudu, the fifth album, was released in Summer 2003 and was the latest in the series of record-breaking recordings from Tarkan. The album, a gift from Tarkan to his fans, steadily climbed the charts & matched Tarkan’s unchallenged record sales. The album release followed with yet another successful tour. Stops included concerts throughout Turkey and international shows in Germany, Moscow, Baku, and Alma Ata.\\n\\nIn terms of sales & popularity, Tarkan is the biggest star of the last decade in Turkey, spilling over his success throughout Europe, South America and Australia. He has charted Top 40 singles, every single year from 1992 to current. His songs, which include “Simarik”, “Sikidim”, “Hup”, “Kuzu Kuzu”, & “Dudu” have become contemporary pop standards. His image has garnered interest & endorsements of international brands such as the omnipresent “Pepsi”, by which Tarkan & his tour were sponsored. Tarkan has also shot two commercials as the face of “Pepsi”. \\n\\nTarkan’s music has always flowed to the beat of pop culture with his heart drumming to its ever-changing pulse. As an artist driven clearly by passion and always one step ahead of the game, Tarkan feels what’s next, he sings what’s next, and he is what’s next.\\n\\nIn addition, Tarkan's team works exclusively with renowned artists in putting together the songs. Along with the stomping electronic beats, he is accompanied by \\\"swaying oboes, melancholy Sufi flutes, and rattling tambourines to a disco rhythm\\\".\\n\\nHis fans, and they can now be found just about anywhere on the planet, find Tarkan \\\"sweet\\\" and that he is \\\"the best.\\\" Tarkan is always friendly to everyone, has a permanently neutral demeanour, never comments on anything, solicits donations for tsunami victims on Turkish MTV, is never arrogant or conceited, and this is why all Turks, from small children to the elderly, love him.\\n\\nTarkan currently shuttles between Istanbul and New York and continues his plans to conquer the international market. The correct reading of this must be \\\"conquer once and for all,\\\" as his \\\"Kiss Kiss\\\" song could likewise be heard throughout Latin America and Central Asia for years. \\n\\nTarkan held stadium performances in Istanbul in 2006, in a five-show run, with audience numbers topping over 200,000. In the unforgettable shows, at one of the biggest stadiums in Turkey, Tarkan was joined on-stage by a troupe of international dancers hailing from America, France, & Turkey, in a breathtaking set design produced by the world-renowned Justin Collie. Fans got a first listen to “Bounce”, Tarkan’s first single off his debut English album and were left speechless.\\n\\nHis English language album entitled, Come Closer was released in 2006 in Europe. His official website proclaims the work to be \\\"a captivating collection of riveting songs, that is a testament to the infinite appeal of an artist who is a true original\\\". \\n\\nThe album is a result of years in the making and a work of collaboration by world class composers, musicians, & technicians, as well as guest appearances by artists and producers such as Wyclef Jean, Pete Martin, Brian Kierulf and Josh Schwartz aka KNS, Devrim Karaoglu, Billy Mann, Lester Mendez, David Werner, Dexter Simmons, the Jettsonz, Supaflyas, Ozan Colakoglu, etc. When asked about recording the album Come Closer in America, Tarkan said,” I live here, this is one of my homes…It felt natural to record in the States.” Come Closer was recorded & mixed at the following studios in the US including, Pacifique Studios, The Hit Factory, Oasis Mastering, Etc. \\n\\nTracks on Come Closer : “Just Like That”, ”In Your Eyes”, “Why Don’t We (Aman Aman)” featuring Whyclef Jean, “Mine”, “Over”, “Start the Fire”, “I Wanna Hear Love Speak (SHHH)”, “Bounce”, “Come Closer”, “Don’t Leave Me Alone”, “Sikidim”, “I’m Gonna Make You Feel Good”, “Mass Confusion”, “Touch”, and “If Only You Knew”.\\n\\nCome Closer brought to the forefront of the international music scene what has been bubbling just under the surface for years. It is an electric combination of unsurpassed talent, unmatched vocal prowess and dexterity, unanticipated and pulsating production, and Tarkan’s unsettling charm and passion. A lethal combination, that after years of simmering is about to boil over & pierce the hearts of anyone exposed. The sweltering eyes that have penetrated the souls of millions of fans are about to ignite…worldwide.\\n\\nTarkan released another Turkish language album on December 25, 2007, which was comprised almost solely of his own work. Called Metamorfoz (\\\"metamorphosis\\\" in English), seven of the songs were written by Tarkan, three other songs were collaborations with long-time engineer and producer Çolakoğlu. The album sold over 300,000 copies in the first two weeks of its release.Tarkan also became the most downloaded artist at TTNetMüzik, a digital music provider where his songs were made legally available in a digital format at the start of January 2008. Out of nearly two million tracks that were purchased after six weeks downloading, nearly 100,000 of those acquired were Tarkan songs, making him the highest grossing single artist at the music portal.\\n \\nUnlike the public's reception, the critical response to Metamorfoz was lukewarm. Most music critics found the album musically lacking from Tarkan's previous albums, though not all. Ecevit Kılıç, columnist for Sabah newspaper, hailed Tarkan on his return and called him the \\\"Orhan Pamuk of music\\\", saying that Tarkan, just like Nobel-awarded Pamuk, always does everything his own way, despite all the attacks he has received from the press.Tarkan received congratulations from the chairman of the Turkish Language Association for his lyricsmanship by using correct language, traditional idioms and sayings in his lyrics, showing it was said a good example to Turkish youth in preserving the culture of the Turkish language. Besides the sales success, Tarkan also produced the third highest viewer rating with his New Year's Eve performance on TRT television channel, generating a total of 1 million YTL income for the television from sponsors.\\n \\nThe album was released in Germany on February 1, 2008, by Urban.[53\\r\\n <a href=\\\"https://www.last.fm/music/Tarkan\\\">Read more on Last.fm</a>. User-contributed text is available under the Creative Commons By-SA License; additional terms may apply.\"}}}";

    @Provides
    public LastFmApi provideAPI() {
        LastFmApi lastFmApi = Mockito.mock(LastFmApi.class);
        Gson gson = new Gson();
        SearchResponse searchResponse = gson.fromJson(mockSearch, SearchResponse.class);
        GetArtistInfoResponse artistInfoResponse = gson.fromJson(mockArtist, GetArtistInfoResponse.class);
        Mockito.when(lastFmApi.search(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Observable.defer(() -> Observable.just(searchResponse)));
        Mockito.when(lastFmApi.getArtistInfo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Observable.defer(() -> Observable.just(artistInfoResponse)));
        return lastFmApi;
    }
}
