/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gurhan.marinatahminweb.model;

/**
 *
 * @author gurhan
 */
public enum HavaDurumu {
    ACIK {

        @Override
        public String toString() {
            return  "Açık";
        }
        
    }, KAPALI {
    
        @Override
        public String toString() {
            return "Kapalı";
        }
        
    }, SAGANAK_YAGISLI {
    
        @Override
        public String toString() {
            return "Sağanak Yağışlı";
        }
        
    }, YAGISLI {
    
        @Override
        public String toString() {
            return "Yağışlı";
        }
    
    }, PARCLALI_COK_BULUTLU {
        @Override
        public String toString() {
            return "Parçalı Çok Bulutlu";
        }
    }
}
