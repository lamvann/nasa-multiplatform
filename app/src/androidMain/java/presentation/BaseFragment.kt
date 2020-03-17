package presentation

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<ViewModelType: ViewModel, BindingType: ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : FragmentActivity(layoutId) {
// Todo Should probably inherit from androidx.Fragment? consider single activity approach
    private val viewModelClass =
        (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<ViewModelType>

    val binding by lazy { DataBindingUtil.setContentView(this, layoutId) as BindingType }

    val viewModel: ViewModelType by lazy {
        ViewModelProvider(this)[viewModelClass]
    }
}