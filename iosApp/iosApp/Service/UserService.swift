import Foundation
import AFNetworking
import shared

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
        let network = CustomNetwork()
        let rawResponse = network.get(url)

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
