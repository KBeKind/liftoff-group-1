"use client";
import React, {useState } from "react";
import axios from "axios";


function getAllShoppingCartPosts() {
    return axios.get("http://localhost:8080/ShoppingCart")
};


export default function shoppingCart2() {
    const [products, setProducts] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = axios.delete("http://localhost:8080/ShoppingCart/remove?Id=" + event.target.id,
                {
                    headers: {
                        accept: "*/*",
                        "Content-Type": "application/json"
                    }

                });
            

            if (response.status === 200) {
                console.log(response.data)
                // useEffect()
            }



        } catch (e) {
            console.log("error", e);
        }

        useEffect(() => {
            const fetchData = async () => {

                try {
                    const response = await getAllShoppingCartPosts();
                    setProducts(response.data);
                } catch (error) {
                    console.log(error);
                } finally {
                    setLoading(false);
                }
            };
            fetchData();
        }, []);
    }




    return (<div>
        <h1>View All Posts</h1>

        <table width='100%'>
            <thead>
                <tr>
                    <th>
                        Picture
                    </th>
                    <th>
                        Name
                    </th>
                    <th width='20%' >
                        Description
                    </th>
                    <th>
                        Category
                    </th>
                    <th>
                        Price
                    </th>
                    <th></th>
                </tr>
            </thead>
            {!loading && (
                <tbody>
                    {products.map((product) => (
                        <tr>
                            <th><img src={product.picture} width={200} /></th>
                            <th>{product.name}</th>
                            <th>{product.description}</th>
                            <th>{product.category}</th>
                            <th> ${product.price}</th>

                            <th>
                                <form onSubmit={handleSubmit} id={product.id}>
                                    {/* <input
                                        name={product.id}
                                        value={product.id}
                                    /> */}
                                    <button type="submit">Remove</button>
                                </form>
                            </th>

                        </tr>


                    ))}
                </tbody>
            )}
        </table>



    </div>)
}