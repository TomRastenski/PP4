const getProducts = () => {
  return fetch("/api/products")
   .then(response => response.json());
}

const createProductHtml = (productData) => {
  const prodTemplate = `
    <div>
        <h4>${productData.name}</h4>
        <img src="https://picsum.photos/id/237/200/300"/ width="100"/>
        <div>
            <span>${productData.price}</span>
            <button data-id="${productData.id}">Add to cart</button>
        </div>
    </div>
  `;
  const listElement = document.createElement('li');
  listElement.innerHTML = prodTemplate.trim();
  return listElement;
}

document.addEventListener("DOMContentLoaded", () => {
    console.log("Lets load some products");
    const productListElement = document.querySelector('#productList');
    getProducts()
     .then(productsAsJson => productsAsJson.map(createProductHtml))
     .then(productsAsHtml => productsAsHtml.forEach(productElement => productListElement.appendChild(productElement)));
});