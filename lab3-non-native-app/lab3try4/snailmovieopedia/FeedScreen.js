import React, {Component, useState} from 'react';
import {FlatList, View, SafeAreaView, StyleSheet, Alert, TouchableOpacity, Text} from "react-native";
import BackgroundWrapper from './BackgroundWrapper';
import ListItem from './ListItem';
import TopBar from './TopBar';

class FeedScreen extends Component {
    constructor() {
        super()
        this.state = {
            movies: 
                [
                { id: 1, title: "In the Mood for Love", director: "Wong Kar-Wai", rating: '9', review: "Every lover feels like his love is something unique and unbeknownst to the entire world.", date: "05-03-2020"}, 
                { id: 2, title: "Night on Earth", director: "Jim Jarmusch", rating: '8', review: "Every lover feels like his love is something unique and unbeknownst to the entire world.", date: "05-03-2020"}, 
                { id: 3, title: "Paterson", director: "Jim Jarmusch", rating: '9', review: "Every lover feels like his love is something unique and unbeknownst to the entire world", date: "05-03-2020"}, 
                { id: 4, title: "Vertigo", director: "Alfred Hitchcock", rating: '8', review: "some", date: "05-03-2020"}, 
                { id: 5, title: "Chungking Express", director: "Wong Kar-wai", rating: '10', review: "some", date: "05-03-2020"}, 
                { id: 6, title: "Pan's labyrinth", director: "Guillermo del Toro", rating: '10', review: "some", date: "05-03-2020"}, 
                { id: 7, title: "Her", director: "Spike Jonze", rating: '10', review: "some", date: "05-03-2020"}, 
                { id: 8, title: "Y tu mamá también", director: "Alfonso Cuarón", rating: '7', review: "some", date: "05-03-2020"}, 
                { id: 9, title: "Beginners", director: "Mike Mills", rating: '8', review: "some", date: "05-03-2020"}, 
                { id: 10, title: "Medianeras", director: "Gustavo Taretto", rating: '7', review: "some", date: "05-03-2020"}, 
                { id: 11, title: "Amelie", director: "Jean-Pierre Jeunet", rating: '9', review: "some", date: "05-03-2020"}, 
                ],
            id: 11,
            movie: '',
        };
    }
        
    renderItem = ({ item }) => (
        <TouchableOpacity onPress={() => this.seeReview(item.title, item.review)}>
            <ListItem id={item.id}
                title={item.title} 
                director={item.director}
                rating={item.rating} 
                date={item.date}
                review={item.review}
                handleDelete={this.handleDelete} 
                handleEdit={this.handleEdit}
            />
        </TouchableOpacity>
    );

    renderHeader = () => (
        <View style={styles.header}>
            <Text style={styles.headerItem}>Title</Text>
            <Text style={styles.headerItem}>Director</Text>
            <Text style={styles.headerItem}>Date</Text>
            <Text style={styles.headerItem}>Rating</Text>
        </View>
    );

    seeReview = (title, review) => {
        console.log(review);
        Alert.alert(
            title,
            review,
            [
                {
                    text: 'Cancel', onPress: () => console.log('Cancel'), style: 'cancel'
                },
            ]
        );
    }

    handleDelete = (id) => {
        const filteredMovies = this.state.movies.filter(movie => movie.id !== id);
        this.setState({ movies: filteredMovies });
    }

    handleEdit = (id, title, director, rating, review, date) => {
        const prevMovie = {
            id: id,
            title: title,
            director: director,
            rating: rating,
            review: review,
            date: date
        };
        console.log(prevMovie);
        this.props.navigation.navigate('Edit', {ex: prevMovie, editMovie: this.editMovie});
    }

    setMovie = (newMovie) => {
        this.setState({movie: newMovie})
    }

    editMovie = (movie) => {
        console.log(movie);
        const updatedMovies = [...this.state.movies]
        const index = updatedMovies.findIndex(m => m.id === movie.id);
        updatedMovies[index] = Object.assign(updatedMovies[index], movie);
        console.log(updatedMovies);
        this.setState({movies: updatedMovies});
    }

    componentDidUpdate() {
        console.log('componentWillUpdate called.');
        if (this.state.movie === ''){
            console.log('oops');
        }
        else{
            const updatedMovies =  [...this.state.movies, this.state.movie];
            this.setState({movies: updatedMovies, movie: ''});
        }
    }

    render(){
        return (
            <BackgroundWrapper>
                <TopBar text="Your Recent Movies"></TopBar>
                <SafeAreaView style={styles.container}>
                    <FlatList 
                        style={styles.list}
                        keyExtractor={(item) => item.id}
                        data = {this.state.movies}
                        renderItem={this.renderItem}
                        ListHeaderComponent={this.renderHeader}
                    />
                </SafeAreaView>
                <TouchableOpacity style={styles.button} onPress={() => {
                         const newId = this.state.id + 1;
                         this.setState({id: newId});
                         this.props.navigation.navigate('Add', {id: newId, setMovie: this.setMovie})}}>
                        <Text style={styles.buttonText}>Add Movie</Text>
                </TouchableOpacity>
                </BackgroundWrapper>
            );
    }
}

const styles = StyleSheet.create({
    container: {
        top: 100
    },
    header: {
        marginLeft: 10,
        marginRight: 40,
        color: 'black',
        backgroundColor: 'grey',
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-between',

    },
    headerItem: {
        fontSize: 10,
        textAlign: 'center',
        color: 'black',
        width: 80,
        height: 30,
        overflow:'scroll'
    },
    list: {
       height: 400,
        overflow: 'scroll',
    },
    button: {
        position: 'absolute',
        width: 126,
        height: 30,
        backgroundColor: '#C4C4C4',
        borderRadius: 15,
        top: 530,
        left: 130
    },
    buttonText: {

        fontSize: 14,
        lineHeight: 17,
        marginTop: 5,
        textAlign: 'center',
    },
    review: {
        fontSize: 60,
        backgroundColor: 'pink',
        width: 300,
        height: 100,
        flex: 1,
    }
});

export default FeedScreen;