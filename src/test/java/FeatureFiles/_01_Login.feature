#  Employee menusu için Create-Exist-Delete bölümleri olan Senaryoyu yazınız
#  Daha sonra aynı senaryoru 5 farklı degerler için çalıştırınız.

Feature: Login Functionality

Scenario:
    Given Navigate to Amazon
    When Enter username and password and click login button
    Then User should login successfully