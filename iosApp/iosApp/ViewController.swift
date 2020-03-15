import UIKit
import app // K/N

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        NasaApi().get {        
//            print($0)
//        }
        SampleIosKt.getNasaData { (data: Item) in
            self.label.text = "date: " + data.date + "\n\n" + "explanation: " + data.explanation + "\n"
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBOutlet weak var label: UILabel!
}
