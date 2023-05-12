package com.example.androidonetask.presentation.utils


import com.example.androidonetask.data.model.album.AlbumListResponse
import com.example.androidonetask.data.model.track.TrackListResponse
import com.example.androidonetask.presentation.model.Item
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class TrackMapperTest {

    private val trackListResponseJson = """
        {
        "results": [
            {
            "name": "Simple Exercice",
            "duration": 340,
            "artist_name": "Both",
            "album_image": "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=241"
            }
        ]
        } 
        """.trimMargin()

    private val albumListResponseJson = """
          {
          "results": [
            {
            "name": "Premiers Jets",
            "artist_name": "TriFace",
            "image": "https://usercontent.jamendo.com?type=album&id=24&width=300"
            }
          ]
          } 
          """.trimIndent()

    private val albumListResponseViewTypeJson = """   
        {
        "results": [
            {   
            "name": "Premiers Jets",
            "artist_name": "TriFace",
            "image": "https://usercontent.jamendo.com?type=album&id=24&width=300"
            },
            {
            "name": "Mind Asylum",
            "artist_name": "Skaut",
            "image": "https://usercontent.jamendo.com?type=album&id=25&width=300"
            },
            {
            "name": "3 Saucisses Dans Une Bulle",
            "artist_name": "TriFace",
            "image": "https://usercontent.jamendo.com?type=album&id=28&width=300"
            },
            {
            "name": "Pouce !",
            "artist_name": "David TMX",
            "image": "https://usercontent.jamendo.com?type=album&id=31&width=300"
            },
            {
            "name": "CD KDO-PYLEFT",
            "artist_name": "Various Artists",
            "image": "https://usercontent.jamendo.com?type=album&id=32&width=300"
            },
            {
            "name": "Simple Exercice",
            "artist_name": "Both",
            "image": "https://usercontent.jamendo.com?type=album&id=33&width=300"
            },
            {
            "name": "The WIRED CD : Rip. Sample. Mash. Share.",
            "artist_name": "Various Artists",
            "image": "https://usercontent.jamendo.com?type=album&id=35&width=300"
            }
        ]
        }
        """.trimMargin()

    private val trackListResponseManyViewTypeJson = """    
        {
        "results": [
        {
            "name": "Simple Exercice",
            "duration": 340,
            "artist_name": "Both",
            "album_image": "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=241"
        },
        {
            "name": "Réveille le Hippie",
            "duration": 248,
            "artist_name": "Both",
            "album_image": "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=242"
        },
        {
            "name": "The Monster",
            "duration": 249,
            "artist_name": "Both",
            "album_image": "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=243"
        },
        {
            "name": "Qui Déchante",
            "duration": 270,
            "artist_name": "Both",
            "album_image": "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=244"
        },
        {
            "name": "J.E.T. Apostrophe A.I.M.E",
            "duration": 155,
            "artist_name": "Both",
            "album_image": "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=245"
        },
        {
            "name": "Take Up Arms",
            "duration": 239,
            "artist_name": "Both",
            "album_image": "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=246"
        },
        {
            "name": "Je le veux aussi",
            "duration": 85,
            "artist_name": "Both",
            "album_image": "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=247"
        }
    ]
}
    """

    private val albumListResponseManyViewType =
        Gson().fromJson(albumListResponseViewTypeJson, AlbumListResponse::class.java)

    private val trackListResponseManyViewType =
        Gson().fromJson(trackListResponseManyViewTypeJson, TrackListResponse::class.java)


    private val albumListResponse =
        Gson().fromJson(albumListResponseJson, AlbumListResponse::class.java)

    private val trackListResponse =
        Gson().fromJson(trackListResponseJson, TrackListResponse::class.java)

    @Test
    fun `return load data list`() {

        val actual = TrackMapper.toUiState(
            trackListResponse, albumListResponse
        )
        val expected = listOf(
            Item.TitleUiModel(1, "Music"),
            Item.TrackUiModel(
                name = "Simple Exercice",
                duration = "340",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=241"
            ),
            Item.TitleUiModel(2, "Albums"),
            Item.PagerUiModel(
                results = listOf(
                    Item.AlbumUiModel(
                        image = "https://usercontent.jamendo.com?type=album&id=24&width=300",
                        name = "Premiers Jets",
                        artistName = "TriFace"
                    )
                )
            ),
            Item.TitleUiModel(3, "Music"),
            Item.TrackUiModel(
                name = "Simple Exercice",
                duration = "340",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=241"
            )
        )
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `return many viewType data list`() {

        val actual = TrackMapper.toUiState(
            trackListResponseManyViewType, albumListResponseManyViewType
        )

        val expected = listOf(
            Item.TitleUiModel(1, "Music"),
            Item.TrackUiModel(
                name = "Simple Exercice",
                duration = "340",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=241"
            ),
            Item.TrackUiModel(
                name = "Réveille le Hippie",
                duration = "248",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=242"
            ),
            Item.TitleUiModel(2, "Albums"),
            Item.PagerUiModel(
                results = listOf(
                    Item.AlbumUiModel(
                        image = "https://usercontent.jamendo.com?type=album&id=24&width=300",
                        name = "Premiers Jets",
                        artistName = "TriFace"
                    ),
                    Item.AlbumUiModel(
                        image = "https://usercontent.jamendo.com?type=album&id=25&width=300",
                        name = "Mind Asylum",
                        artistName = "Skaut"
                    ),
                    Item.AlbumUiModel(
                        image = "https://usercontent.jamendo.com?type=album&id=28&width=300",
                        name = "3 Saucisses Dans Une Bulle",
                        artistName = "TriFace"
                    ),
                    Item.AlbumUiModel(
                        image = "https://usercontent.jamendo.com?type=album&id=31&width=300",
                        name = "Pouce !",
                        artistName = "David TMX"
                    ),
                    Item.AlbumUiModel(
                        image = "https://usercontent.jamendo.com?type=album&id=32&width=300",
                        name = "CD KDO-PYLEFT",
                        artistName = "Various Artists"
                    ),
                    Item.AlbumUiModel(
                        image = "https://usercontent.jamendo.com?type=album&id=33&width=300",
                        name = "Simple Exercice",
                        artistName = "Both"
                    ),
                    Item.AlbumUiModel(
                        image = "https://usercontent.jamendo.com?type=album&id=35&width=300",
                        name = "The WIRED CD : Rip. Sample. Mash. Share.",
                        artistName = "Various Artists"
                    )
                )
            ),
            Item.TitleUiModel(3, "Music"),
            Item.TrackUiModel(
                name = "Simple Exercice",
                duration = "340",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=241"
            ),
            Item.TrackUiModel(
                name = "Réveille le Hippie",
                duration = "248",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=242"
            ),
            Item.TrackUiModel(
                name = "The Monster",
                duration = "249",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=243"
            ),
            Item.TrackUiModel(
                name = "Qui Déchante",
                duration = "270",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=244"
            ),
            Item.TrackUiModel(
                name = "J.E.T. Apostrophe A.I.M.E",
                duration = "155",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=245"
            ),
            Item.TrackUiModel(
                name = "Take Up Arms",
                duration = "239",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=246"
            ),
            Item.TrackUiModel(
                name = "Je le veux aussi",
                duration = "85",
                artistName = "Both",
                albumImage = "https://usercontent.jamendo.com?type=album&id=33&width=300&trackid=247"
            )
        )
        Assert.assertEquals(expected, actual)
    }
}