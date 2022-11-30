import {
    GET_PRODUCTS,
    CREATE_PRODUCTS,
    UPDATE_PRODUCTS,
    DELETE_PRODUCTS,
    GET_CARTS,
    CART_PRODUCTS,
  } from "./types";
  
  import axios from "axios";
  
  export const getProducts = () => dispatch => {
    //console.log(localStorage.getItem("token"));
    return axios
      .get("http://localhost:8080/item")
      .then(res => {
        dispatch({
          type: GET_PRODUCTS,
          payload: res.data
        });
        //console.log(res.data);
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  export const createProducts = PRODUCTS => dispatch => {
    axios
      .post("http://localhost:8080/admin/item", PRODUCTS,
       {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      }
      )
      .then(res => {
        dispatch({
          type: CREATE_PRODUCTS
        });
        alert("Added one product successfully");
        // dispatch(getProducts());
      })
      .catch(err => {
        console.log(err);
        alert("Try Again");
      });
  };
  
  export const updateProducts = products => dispatch => {
    axios
      .put(
        "http://localhost:8080/cart/" + products.id,
        products,
        {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
        }
      )
      .then(res => {
        dispatch({
          type: UPDATE_PRODUCTS
        });
        dispatch(getProducts());
        alert("Updated Successfully");
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  export const deleteProducts = itemName => dispatch => {
    axios
      .delete("http://localhost:8080/cart/" + itemName, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      })
      .then(res => {
        dispatch({
          type: DELETE_PRODUCTS
        });
        dispatch(getProducts());
        alert("Deleted Successfully");
      })
      .catch(err => {
        console.log(err);
      });
  };

  export const AddtoCart = PRODUCTS => dispatch => {
    axios
      .post("http://localhost:8080/cart", PRODUCTS,
       {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      }
      )
      .then(res => {
        dispatch({
          type: CART_PRODUCTS
        });
        alert("Product Added to Cart");
        // dispatch(getProducts());
      })
      .catch(err => {
        console.log(err);
        alert("Try Again");
      });
  };
  
  export const getCarts = () => dispatch => {
    console.log(localStorage.getItem("token"));
    return axios
      .get("http://localhost:8080/cart",
      {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      }
      )
      .then(res => {
        dispatch({
          type: GET_CARTS,
          payload: res.body
        });
        console.log(res.body);
      })
      .catch(err => {
        console.log(err);
      });
  };



  
