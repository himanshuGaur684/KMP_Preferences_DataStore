import SwiftUI
import shared

struct ContentView: View {
    
    
    let cacheMangaer = PrefCacheManager(dataStore: IosDataStore.shared.getCreateDataStore())
    
	let greet = Greeting().greet()

    
    @State private var key = ""
    @State private var value = ""
    
    @State private var savedValue = ""
    
    
	var body: some View {
        VStack{
            
            Text(savedValue)
                .font(.system(size: 15))
                .padding(.vertical,10)
            
            TextField("Key",text: $key)
                .textFieldStyle(.roundedBorder)
                .padding(.vertical,8)
            
            TextField("Value", text: $value)
                .textFieldStyle(.roundedBorder)
                .padding(.vertical,8)
            
            Button(action: {
                Task{
                    do{
                        try await cacheMangaer.save(key: key, value: value)
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
                        self.savedValue =  try await cacheMangaer.getValue(key: key)
                    }catch{
                        print(error)
                    }
                }
            }, label: {
                Text("Get")
            })
            
            
        }.padding(12)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
