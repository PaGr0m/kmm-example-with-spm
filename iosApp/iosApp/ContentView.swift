import SwiftUI
import shared
import AFNetworking

struct ContentView: View {
    @EnvironmentObject var userService: UserService
    
    var body: some View {
        ZStack {
            LinearGradient(
                gradient: Gradient(colors: [Color(#colorLiteral(red: 0.2588235438, green: 0.7568627596, blue: 0.9686274529, alpha: 1)), Color(#colorLiteral(red: 0.721568644, green: 0.8862745166, blue: 0.5921568871, alpha: 1)).opacity(0.6)]),
                startPoint: .topLeading,
                endPoint: .trailing
            ).ignoresSafeArea()
                    
            VStack(alignment: .leading) {
                Text("All users")
                    .font(.title)
                    .bold()
                    .padding(.top, 40.0)
                
                ScrollView(showsIndicators: false) {
                    LazyVStack (alignment: .leading) {
                        ForEach(userService.users) { user in
                            HStack(alignment:.top) {
                                Text("\(user.id)")
                                VStack(alignment: .leading) {
                                    Text(user.name).bold()
                                    Text(user.email.lowercased())
                                    Text(user.phone)
                                    
                                }
                            }
                            .padding(.all)
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .background(Color(#colorLiteral(red: 0.9764705896, green: 0.850980401, blue: 0.5490196347, alpha: 1)))
                            .cornerRadius(20)
                        }
                    }
                    .padding(.trailing, 16.0)
                    .onAppear {
                        userService.getUsers()
                    }
                }
            }
            .navigationBarHidden(true)
            .padding(.leading)
        }
        
        //let msg = userPrint()
        //Text("hi")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView().environmentObject(UserService())
    }
}
