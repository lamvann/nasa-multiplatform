import UIKit
import app // K/N

class SplashViewController: UIViewController {
    
    private var splashViewModel: SplashViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initViewModel()
        splashViewModel.getPlanetaryData{ (data: PlanetaryResponse) in
            self.label.text = "date: " + data.date + "\n\n" + "explanation: " + data.explanation + "\n"
        }
    }
    
    private func initViewModel() {
        splashViewModel = SplashViewModel()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBOutlet weak var label: UILabel!
}
