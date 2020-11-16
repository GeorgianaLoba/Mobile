import React, {Component} from 'react'
import {View, StyleSheet, Image, Text, TouchableOpacity, Alert} from "react-native";

class ListItem extends Component {
    constructor(props){
        super(props);
        this.handleDelete = this.props.handleDelete.bind(this);
        this.handleEdit = this.props.handleEdit.bind(this);
    }

     render () {
        const { id, title, director, rating, date, review } = this.props;
        const movie = {id, title, director, rating, date, review}
        return (
            <View style={styles.container}>
            <Text style={styles.text}>{title}</Text>
            <Text style={styles.text}>{director}</Text>
            <Text style={styles.text}>{date}</Text>
            <Text style={styles.text}>{rating}</Text>
            <View style={styles.icon}>
            <TouchableOpacity onPress={() => this.handleEditAuxiliary(movie)}>
                <Image style={styles.edit} source={require('../assets/images/edit.png')}/></TouchableOpacity>
            <TouchableOpacity  onPress={() => this.handleDeleteAuxiliary(id)}>
                <Image style={styles.trash} source={require('../assets/images/trash.png')}/></TouchableOpacity>
       </View>
    </View>
    )};
     
    handleDeleteAuxiliary(id) {
        Alert.alert(
            'Delete Movie',
            'Are you sure you want to delete this movie?',
            [
                {
                    text: 'Cancel', onPress: () => console.log('Cancel'), style: 'cancel'
                },
                {
                    text: 'Yes', onPress: () => this.handleDelete(id)
                },
            ],
            {cancelable: false}
        )
    };

    handleEditAuxiliary(movie) {
        Alert.alert(
            'Edit Movie',
            'Are you sure you want to edit this movie?',
            [
                {
                    text: 'Cancel', onPress: () => console.log('Cancel'), style: 'cancel'
                },
                {
                    text: 'Yes', onPress:() => this.handleEdit(movie.id, movie.title, movie.director, movie.rating, movie.review, movie.date)
                },
            ],
            {cancelable: false}
        )
    };
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-between',
        flexWrap: 'wrap',
        marginLeft: 10,
        marginRight: 10,
        marginTop: 5,
    },
    text:{
        fontSize: 10,
        textAlign: 'center',
        color: 'black',
        backgroundColor: '#91BA53',
        width: 80,
        height: 30,
        overflow:'scroll'
    },
    icon:{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'flex-start',
        flexWrap: 'wrap',

    },
    edit: {
        width: 17,
        height: 17,
    },
    trash: {
        width: 17,
        height: 17,
    }    
});

export default ListItem;