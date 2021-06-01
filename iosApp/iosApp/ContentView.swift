import SwiftUI
import shared
import AFNetworking

struct ContentView: View {
    var body: some View {
        let response = swiftRequest()
        let user = serialize(object: response)
        Text("hi")
    }
    
    func request() -> String {
        let url = "https://jsonplaceholder.typicode.com/posts/1"
        let response = CustomNetwork().get(url: url)
        
        return response
    }
    
    func swiftRequest() -> Any {
        let url = "https://jsonplaceholder.typicode.com/users/1"
        var rawResponse : Any = ""
        
        let semaphore = DispatchSemaphore(value: 0);
        let manager = AFHTTPSessionManager()
        
        manager.completionQueue = DispatchQueue.global();
        manager.responseSerializer = AFJSONResponseSerializer()
        
        let dataTask = manager.get(
            url,
            parameters: nil,
            headers: nil,
            progress: nil,
            success:
                { operation, responseObject in
                    rawResponse = responseObject
                    semaphore.signal()
                },
            failure:
                { dataTask, error in
                    print(error)
                }
        )

        semaphore.wait()
        
        dataTask?.resume()
                    
        return rawResponse
    }
    
    func serialize(object: Any) -> User {
        let json = object as? [String: Any]
        let jsonData = try! JSONSerialization.data(withJSONObject: json, options: [])
        let user: User = try! JSONDecoder().decode(User.self, from: jsonData)
        
        print("success: ", user.email)
        
        return user
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
