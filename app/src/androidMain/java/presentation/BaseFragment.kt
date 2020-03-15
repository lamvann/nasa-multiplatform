package presentation

import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VM: ViewModel>(
    @LayoutRes val layoutId: Int
) : FragmentActivity(layoutId) {

    private val viewModelClass =
        (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>

    val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }
}