import Foundation
import AFNetworking

class UserService: ObservableObject {
    @Published var users: [User] = []
    
    func getUsers() {
        var tmpUsers: [User] = []
        for id in 1...10 {
            let url = "https://jsonplaceholder.typicode.com/users/" + String(id)
            let response = request(url: url)
            let user = serialize(object: response)
            tmpUsers.append(user)
        }
        
        self.users = tmpUsers
    }
    
    private func request(url: String) -> Any {
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
                    rawResponse = responseObject as Any
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
    
    private func serialize(object: Any) -> User {
        let json = object as? [String: Any]
        let jsonData = try! JSONSerialization.data(withJSONObject: json as Any, options: [])
        let user: User = try! JSONDecoder().decode(User.self, from: jsonData)
        
        print("success: ", user.email)
        
        return user
    }
}
