import React from 'react';
import {
    StyleSheet, View,
} from 'react-native';

const BackgroundWrapper = ({ children }) => {
    return(
        <View style={styles.background}>
            {children}    
        </View>
    );
};

const styles = StyleSheet.create({
    background: {
        backgroundColor: '#660303',
        borderWidth: 5,
        borderColor: 'black',
        flex: 1,
    }
});

export default BackgroundWrapper;
