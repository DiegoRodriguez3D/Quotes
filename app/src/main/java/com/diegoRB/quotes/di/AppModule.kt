package com.diegoRB.quotes.di

import com.diegoRB.quotes.data.repository.AuthRepositoryImpl
import com.diegoRB.quotes.data.repository.QuotesRepositoryImpl
import com.diegoRB.quotes.data.repository.UsersRepositoryImpl
import com.diegoRB.quotes.core.Constants.QUOTES
import com.diegoRB.quotes.core.Constants.USERS
import com.diegoRB.quotes.domain.repository.AuthRepository
import com.diegoRB.quotes.domain.repository.QuotesRepository
import com.diegoRB.quotes.domain.repository.UsersRepository
import com.diegoRB.quotes.domain.use_cases.auth.*
import com.diegoRB.quotes.domain.use_cases.quotes.*
import com.diegoRB.quotes.domain.use_cases.users.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(
        USERS)

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(QUOTES)
    fun provideStorageQuotesRef(storage: FirebaseStorage): StorageReference = storage.reference.child(
        QUOTES)

    @Provides
    @Named(QUOTES)
    fun provideQuotesRef(db: FirebaseFirestore): CollectionReference = db.collection(QUOTES)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideQuotesRepository(impl: QuotesRepositoryImpl): QuotesRepository = impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        singup = SingUp(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    fun provideQuotesUseCases(repository: QuotesRepository) = QuotesUseCases(
        create = CreateQuote(repository),
        update = UpdateQuote(repository),
        delete = DeleteQuote(repository),
        getQuotes = GetQuotes(repository),
        getQuotesByIdUser = GetQuotesByIdUser(repository),
        likeQuote = LikeQuote(repository),
        unlikeQuote = UnlikeQuote(repository)
    )
}