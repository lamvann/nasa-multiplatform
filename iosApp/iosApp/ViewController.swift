import UIKit
import app

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        NasaApi().info { (result: Item) in
//            DispatchQueue.main.async {
//                self.label.text = result.explanation
//            }
//        }
        
        label.text = Proxy().proxyHello()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBOutlet weak var label: UILabel!
}
