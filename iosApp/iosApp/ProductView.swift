//
//  ProductView.swift
//  iosApp
//
//  Created by Otamurod Safarov on 08/05/24.
//  Copyright © 2024. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct ProductView: View {
    // immutable variable
    let product: ProductResponse
    
    var body: some View {
        // Vertical Stack
        VStack(alignment: .leading){
            AsyncImage(url: URL(string: product.image)){ imageUrl in
                if let image = imageUrl.image{
                    image
                        .resizable()
                        .scaledToFill()
                        .animation(.default, value: image)
                        .accessibility(hidden: false)
                        .accessibilityLabel(Text("Product Thumbnail"))
                } else if imageUrl.error != nil{
                    VStack{
                        Text("Image not available.")
                            .font(.title3)
                    }
                } else {
                    ProgressView().frame(maxWidth:.infinity)
                }
            }
            
            Text(product.title)
                .multilineTextAlignment(.leading)
                .lineLimit(2)
                .truncationMode(.tail)
                .font(.title2.bold())
                .padding(.top, 10)
            
            Text(product.description_)
                .multilineTextAlignment(.leading)
                .lineLimit(6)
                .truncationMode(.tail)
                .font(.title2.bold())
                .padding(.top, 5)
                .padding(.bottom, 10)
            
            // Horizontal Stack
            HStack {
                Text(product.category)
                    .font(.body.italic())
                
                Spacer()
                
                Text("$\(String(format: "%.2f", product.price))")
                    .font(.body.bold())
            }
            .padding(.vertical, 10)
        }
    }
}

struct ProductView_Preview: PreviewProvider {
    static var previews: some View {
        
        let sampleProduct = ProductResponse(
            id: 1,
            title: "There will be title",
            price: 100.0,
            description: "There will be the product description",
            category: "Men",
            image: ""
        )
        
        ProductView(product: sampleProduct).padding()
    }
}
