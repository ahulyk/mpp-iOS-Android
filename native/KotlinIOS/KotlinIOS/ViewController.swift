//
//  ViewController.swift
//  KotlinIOS
//
//  Created by Evgeny Petrenko on 26.09.18.
//  Copyright © 2018 Evgeny Petrenko. All rights reserved.
//

import UIKit
import SharedCode

class ViewController: UIViewController {

    @IBOutlet weak var textView: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    @IBAction func loadData(_ sender: Any, forEvent event: UIEvent) {
        self.textView.text = nil
        self.textView.text = "dsdsdsdsds"

        CommonKt.makeRestCall { (result) -> KotlinUnit in
//            DispatchQueue.main.async {
                self.textView.text = result.description
//            }
            return KotlinUnit()
        }
    }
    
//    func playSound(soundUrl: String) {
//        let sound = URL(fileURLWithPath: soundUrl)
//        do {
//            let audioPlayer = try AVAudioPlayer(contentsOf: sound)
//            audioPlayer.prepareToPlay()
//            audioPlayer.play()
//        }catch let error {
//            print("Error: \(error.localizedDescription)")
//        }
//    }
    
}

