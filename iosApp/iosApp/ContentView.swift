import SwiftUI
import shared

struct ContentView: View {

    let storeManager = StoreManager(store: PreDataStoreForIos.shared.getDataStore())
    
    
    @State private var key = ""
    @State private var value = ""
    @State private var savedValue  = ""
    
	let greet = Greeting().greet()

	var body: some View {
        VStack{

            Text(savedValue)
                .font(.system(size: 15))
            
            TextField("enter key...", text: $key)
                .textFieldStyle(.roundedBorder)  
            
            TextField("enter value...", text: $value)
                .textFieldStyle(.roundedBorder)
            
            Button(action: {
                Task{
                    do{
                        try await storeManager.save(key: key, value: value)
                    }catch{
                        print(error)
                    }
                }
            }, label: {
                Text("Save")
            })
            
            Button(action: {
                Task{
                    do{
                        let savedInfo =  try await storeManager.getValue(key: key)
                        DispatchQueue.main.async{
                            savedValue = savedInfo
                        }
                       
                    }catch{
                        print(error)
                    }
                }
            }, label: {
                Text("Get")
            })
            
            
        }
        .padding(12)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
