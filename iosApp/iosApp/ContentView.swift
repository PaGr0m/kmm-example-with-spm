import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        Text(request())
    }
    
    func request() -> String {
        let url = "https://jsonplaceholder.typicode.com/posts/1"
        let response = CustomNetwork().get(url: url)

        return response
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
