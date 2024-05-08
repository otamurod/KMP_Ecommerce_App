import SwiftUI
import shared

struct ContentView: View {
    @StateObject
    var viewModel = HomeViewModel()

	var body: some View {
        VStack {
            if viewModel.response?.isSuccess() == true {
                // Similar to LazyColumn in Jetpack Compose
                List(viewModel.response?.getProducts().items ?? [], id: \.id) { element in
                    ProductView(product: element)
                }
            } else if viewModel.response?.isError() == true {
                VStack {
                    Spacer()
                    
                    Text("\(viewModel.response?.getErrorMessage() ?? "Unknown Error" )")
                        .font(.title3)
                        .fontWeight(.bold)
                    
                    Spacer()
                }.frame(minWidth: .infinity, minHeight: .infinity)
            } else if viewModel.response?.isLoading() == true {
                VStack {
                    Spacer()
                    
                    ProgressView("Loading")
                        .progressViewStyle(CircularProgressViewStyle())
                        .padding()
                    
                    Spacer()
                }.frame(maxWidth: .infinity, maxHeight: .infinity)
            }
        }.task {
            await viewModel.fetchData()
        }
	}
}

class HomeViewModel: ObservableObject {
    // private(set) means that setter of a variable 'response' is can be invoked within the class  itself since it is private
    // @Published is used to notify/update the UI automatically whenever the value of the variable changes
    // It is useful since we do not need extra work to observe the changes explicitly
    @Published
    private(set) var response: RequestState? = nil
    
    // @MainActor means that the function will be exucuted in the Main Thread
    // async keyword is used not to block the UI (Main Thread) & await keyword ensures that the function will wait for the result of the API call
    @MainActor
    func fetchData() async {
        for await requestState in ProductsApi().fetchProducts(limit: 10){
            response = requestState
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
