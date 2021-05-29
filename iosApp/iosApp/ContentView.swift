import SwiftUI
import KotlinLibrary

struct ContentView: View {
    var body: some View {
        let network = KotlinLibrary.Network()
        let response = request()
        
        Text(network.hello())
    }
    
    func request() -> String {
        KotlinLibrary.Network().restGet()
        return "request"
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
