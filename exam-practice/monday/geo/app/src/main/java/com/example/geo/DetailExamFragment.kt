package com.example.geo

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.geo.databinding.DetailExamContentBinding
import com.example.geo.domain.Exam
import java.util.*


/**
 * A fragment representing a single Book detail screen.
 * This fragment is either contained in a [BookListActivity]
 * in two-pane mode (on tablets) or a [EventDetailActivity]
 * on handsets.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */

//todo THIS IS USELESS in my app
class DetailExamFragment : Fragment() {

  /**
   * The dummy content this fragment is presenting.
   */
  private lateinit var exam: Exam
  private lateinit var binding: DetailExamContentBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }


  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    const val ARG_ITEM_ID = "item_id"
  }
}
