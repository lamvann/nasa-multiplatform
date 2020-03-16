import UIKit
import app // K/N
import MapleBacon

class SplashViewController: UIViewController {
    
    private var splashViewModel: SplashViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initViewModel()
        // TODO observe instead of passing closure
        splashViewModel.getPlanetaryData{ (data: PicOfTheDay) in
            
            self.background.setImage(with: URL(string: data.hdurl))
            self.background.contentMode = UIViewContentMode.scaleAspectFill
            self.background.layer.masksToBounds = true
            
            self.label.text = data.title
        }
    }
    
    private func initViewModel() {
        splashViewModel = SplashViewModel()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var background: UIImageView!
}
