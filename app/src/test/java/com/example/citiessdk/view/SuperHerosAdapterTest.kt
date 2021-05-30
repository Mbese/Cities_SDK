package com.example.citiessdk.view

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.citiessdk.R
import com.example.citiessdk.model.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks

class SuperHerosAdapterTest {

    @Mock
    private lateinit var mockView: View

    @Mock
    private lateinit var mockNameTextView: TextView
    @Mock
    private lateinit var mockFullNameTextView: TextView
    @Mock
    private lateinit var mockImageView: ImageView
    @Mock private lateinit var mockSuperHero: SuperHero
    @Mock private lateinit var mockBiography: Biography
    @Mock private lateinit var mockConnections: Connections
    @Mock private lateinit var mockPowerstats: Powerstats
    @Mock private lateinit var mockWork: Work
    @Mock private lateinit var mockImage: Image
    @Mock private lateinit var mockAppearance: Appearance
    @Mock private lateinit var mockListener: SuperHerosAdapter.ItemClickListener

    @Mock private lateinit var mockActivity: Activity


    private lateinit var viewHolder: SuperHerosAdapter.MyViewHolder

    private lateinit var adapter: SuperHerosAdapter

    @Before
    fun setUp() {
        initMocks(this)

        doReturn(mockNameTextView).`when`(mockView).findViewById<TextView>(R.id.name)
        doReturn(mockFullNameTextView).`when`(mockView).findViewById<TextView>(R.id.fullName)
        doReturn(mockImageView).`when`(mockView).findViewById<ImageView>(R.id.thumbnail)

        val superheros = ArrayList<SuperHero>()
        superheros.add(mockSuperHero)

        adapter = SuperHerosAdapter(mockActivity, superheros, mockListener)
        viewHolder = SuperHerosAdapter.MyViewHolder(mockView)

        `when`(mockView.context).thenReturn(mockActivity)
    }

    @Test
    fun superheroList_should_contain_all_the_superheros_that_are_passed_through() {
        val superheroList = arrayListOf(
            SuperHero(
                "success",
                "1",
                "superhero1",
                mockPowerstats,
                mockBiography,
                mockAppearance,
                mockWork,
                mockConnections,
                mockImage
            ),
            SuperHero(
                "success",
                "2",
                "superhero2",
                mockPowerstats,
                mockBiography,
                mockAppearance,
                mockWork,
                mockConnections,
                mockImage
            )
        )

        adapter = SuperHerosAdapter(mockActivity, superheroList, mockListener)

        Assert.assertEquals(2, adapter.itemCount)
    }

    @Test
    fun onCreateViewHolder_should_inflate_superhero_list_item() {
        val mockViewGroup = mock(ViewGroup::class.java)
        val mockLayoutInflater = mock(LayoutInflater::class.java)
        adapter = spy(adapter)

        `when`(mockViewGroup.context).thenReturn(mock(Context::class.java))
        `when`(
            mockLayoutInflater.inflate(anyInt(),
                any(ViewGroup::class.java),
                anyBoolean()
            )
        ).thenReturn(mockView)

        doReturn(mockLayoutInflater).`when`(adapter).getLayoutInflater(mock(Context::class.java))

        adapter.onCreateViewHolder(mockViewGroup, 0)

        verify(mockLayoutInflater).inflate(R.layout.list_row, mockViewGroup, false)
    }

    @Test
    fun onBindViewHolder_views_should_be_bound_to_superhero_name() {
        val viewHolder = SuperHerosAdapter.MyViewHolder(mockView)
        val superheroList = arrayListOf(
            SuperHero(
                "success",
                "1",
                "superhero1",
                mockPowerstats,
                mockBiography,
                mockAppearance,
                mockWork,
                mockConnections,
                mockImage
            )
        )
        adapter = SuperHerosAdapter(mockActivity,superheroList, mockListener)

        adapter.onBindViewHolder(viewHolder, 0)

        verify(mockNameTextView)
    }

    @Test
    fun onBindViewHolder_views_should_be_bound_to_superhero_full_name() {
        val viewHolder = SuperHerosAdapter.MyViewHolder(mockView)
        val superheroList = arrayListOf(
            SuperHero(
                "success",
                "1",
                "superhero1",
                mockPowerstats,
                mockBiography,
                mockAppearance,
                mockWork,
                mockConnections,
                mockImage
            )
        )
        adapter = SuperHerosAdapter(mockActivity,superheroList, mockListener)

        adapter.onBindViewHolder(viewHolder, 0)

        verify(mockFullNameTextView)
    }

    @Test
    fun onBindViewHolder_views_should_be_bound_to_image() {
        val viewHolder = SuperHerosAdapter.MyViewHolder(mockView)
        val superheroList = arrayListOf(
            SuperHero(
                "success",
                "1",
                "superhero1",
                mockPowerstats,
                mockBiography,
                mockAppearance,
                mockWork,
                mockConnections,
                mockImage
            )
        )

        adapter = SuperHerosAdapter(mockActivity,superheroList, mockListener)

        adapter.onBindViewHolder(viewHolder, 0)

        verify(mockImageView)
    }

}