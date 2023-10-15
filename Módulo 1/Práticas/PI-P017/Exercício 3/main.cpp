#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Product
{
private:
    string productName;
    float productPrice;
    int productCode;
    int productId;
    static int nextProductId;

public:
    Product()
    {
        setProductId(nextProductId++);
    }

    static Product registerProduct()
    {
        Product newProduct;
        string prodName;
        int prodCode;
        float prodPrice;

        cout << "Digite o nome do produto: ";
        cin.ignore();
        getline(cin, prodName);

        cout << "Digite o preço do produto: ";
        cin >> prodPrice;

        cout << "Digite o código do produto: ";
        cin >> prodCode;

        newProduct.setProductName(prodName);
        newProduct.setProductPrice(prodPrice);
        newProduct.setProductCode(prodCode);

        return newProduct;
    }

    string getProductName()
    {
        return productName;
    }

    float getProductPrice()
    {
        return productPrice;
    }

    int getProductCode()
    {
        return productCode;
    }

    int getProductId()
    {
        return productId;
    }

    void setProductName(string prodName)
    {
        this->productName = prodName;
    }

    void setProductPrice(float prodPrice)
    {
        this->productPrice = prodPrice;
    }

    void setProductCode(int prodCode)
    {
        this->productCode = prodCode;
    }

    void setProductId(int prodId)
    {
        this->productId = prodId;
    }

    void listProducts()
    {
        cout
            << "Id do produto: " << getProductId() << endl
            << "Nome do produto: " << getProductName() << endl
            << "Preço do produto: " << getProductPrice() << endl
            << "Código do produto: " << getProductCode() << endl
            << endl;
    }

    void listProductPerCode(int code)
    {

        if (getProductCode() == code)
        {
            cout
                << "Id do produto: " << getProductId() << endl
                << "Nome do produto: " << getProductName() << endl
                << "Preço do produto: " << getProductPrice() << endl
                << "Código do produto: " << getProductCode() << endl
                << endl;
        }
    }

    static void resetNextProductId()
    {
        nextProductId = 1;
    }
};

int Product::nextProductId = 1;

class Stock
{
private:
    int amountItem;
    vector<Product> productsCart;

public:
    int getAmountItem()
    {
        return amountItem;
    }

    vector<Product> getProductsInCart()
    {
        return productsCart;
    }

    void setAmountItem(int amount)
    {
        this->amountItem = amount;
    }

    void setProductsInCart(Product product)
    {
        this->productsCart.push_back(product);
    }
};

class ShoppingCart
{
private:
    int amountItem;
    vector<Product> productsCart;
};

int main()
{
    vector<Product> products;
    int choice;

    do
    {
        cout << "----- Menu----- :" << endl
             << endl
             << "1. Registrar novo produto" << endl
             << "2. Listar produto por código" << endl
             << "3. Listar todos os produtos" << endl
             << "4. Sair" << endl
             << "Escolha a opção: ";
        cin >> choice;

        cout << endl;

        switch (choice)
        {
        case 1:
        {
            products.push_back(Product::registerProduct());
        }
        break;
        case 2:
        {
            int code;

            cout << "Digite o código do produto: ";
            cin >> code;

            for (Product p : products)
            {
                p.listProductPerCode(code);
            }
        }
        break;
        case 3:
        {
            for (Product p : products)
            {
                p.listProducts();
            }
        }
        break;
        case 4:
        {
            cout << "Saindo..." << endl;
        }
        break;
        default:
            cout << "Opção inválida. Tente novamente." << endl;
            break;
        }
    } while (choice != 4);

    return 0;
}