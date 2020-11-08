package tk.laurenfrost.holodos.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_form.view.*
import tk.laurenfrost.holodos.R
import tk.laurenfrost.holodos.domain.entity.Item
import tk.laurenfrost.holodos.interfaces.OnItemFormSubmitListener

class ItemFormDialogFragment : DialogFragment() {
    private val gson = Gson()
    var item: Item? = null
    private lateinit var onItemFormSubmitListener: OnItemFormSubmitListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onItemFormSubmitListener = context as OnItemFormSubmitListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.item_form, null)

        val json = arguments?.getString("item")
        if (json != null) {
            item = gson.fromJson(json, Item::class.java)
            view.editItemName.setText(item!!.name)
            view.editItemQuantity.setText(item!!.quantity.toString())
        }

        return builder
            .setTitle("Диалоговое окно")
            .setView(view)
            .setPositiveButton("OK") { dialog, which ->
                val newItem = if (item != null) {
                    Item(
                        item!!.id,
                        view.editItemName.text.toString(),
                        view.editItemQuantity.text.toString().toInt(),
                        item!!.boardId
                    )
                } else {
                    Item(
                        "",
                        view.editItemName.text.toString(),
                        view.editItemQuantity.text.toString().toInt(),
                        arguments?.getString("boardId")!!
                    )
                }
                onItemFormSubmitListener.onItemFormSubmit(newItem)
            }
            .setNegativeButton("Отмена", null)
            .create()
    }
}
