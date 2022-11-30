import { Form, Button} from "react-bootstrap"
import { useState, useContext } from "react"

import {product} from '../actions/products' ;

const EditItem = ( {theProduct}) => {

    const id = theProduct.id;

    const [name, setName] = useState(theProduct.itemName)
    const [quantity, setQuantity] = useState(theProduct.quantity)
    const [price, setPrice] = useState(theProduct.price)

    const {updateItem} = useContext(product);

    const updatedItem = {name, quantity, price}

    const handleSubmit = (e) => {
        e.preventDefault();
        updateItem(name, updatedItem)
    }

    return (
        <Form>
            <Form.Group>
                <Form.Control
                type = "text"
                placeholder="Product Name"
                name="name"
                value={name}
                onChange={(e)=> setName(e.target.value)}
                />
            </Form.Group>
            <Form.Group>
                <Form.Control
                type = "text"
                placeholder="Quantity"
                name="quantity"
                value={quantity}
                onChange={(e)=> setQuantity(e.target.value)}
                />
            </Form.Group>
            <Form.Group>
                <Form.Control
                type = "text"
                placeholder="Price"
                name="price"
                value={price}
                onChange={(e)=> setPrice(e.target.value)}
                />
            </Form.Group>
            <Button variant="success" type="submit" block>
                Edit Employee
            </Button>
        </Form>

    )
}

export default EditItem;