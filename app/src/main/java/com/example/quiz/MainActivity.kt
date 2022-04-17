package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var kuis: TextView? = null
    var rg: RadioGroup? = null
    var PilihanA: RadioButton? = null
    var PilihanB: RadioButton? = null
    var PilihanC: RadioButton? = null
    var PilihanD: RadioButton? = null
    var nomor = 0

    //pertanyaan
    var pertanyaan_kuis = arrayOf(
        "1. Komputer pertama kali ditemukan pada tahun?",
        "2. Siapa nama pendiri Microsoft?",
        "3. Ibukota Indonesia adalah",
        "4. Yang merupakan operator logika pada kotlin adalah?",
        "5. Di bawah ini yang merupakan perusahaan teknologi di indonesia,kecuali?"
    )

    //pilihan jawaban a, b, c, d
    var pilihan_jawaban = arrayOf(
        "1928", "1887", "1822", "1908",
        "Steve Jobs", "Bill Gates", "Mark Zuckerberg", "Larry Page",
        "Jakarta", "Bogor", "Tangerang", "Bekasi",
        "||", "!!", "*", "+",
        "Facebook", "Gojek", "Tokopedia", "Bukalapak"
    )

    //jawaban benar
    var jawaban_benar = arrayOf(
        "1822",
        "Bill Gates",
        "Jakarta",
        "||",
        "Facebook"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kuis = findViewById<View>(R.id.kuis) as TextView
        rg = findViewById<View>(R.id.pilihan) as RadioGroup
        PilihanA = findViewById<View>(R.id.pilihanA) as RadioButton
        PilihanB = findViewById<View>(R.id.pilihanB) as RadioButton
        PilihanC = findViewById<View>(R.id.pilihanC) as RadioButton
        PilihanD = findViewById<View>(R.id.pilihanD) as RadioButton
        kuis!!.text = pertanyaan_kuis[nomor]
        PilihanA!!.text = pilihan_jawaban[0]
        PilihanB!!.text = pilihan_jawaban[1]
        PilihanC!!.text = pilihan_jawaban[2]
        PilihanD!!.text = pilihan_jawaban[3]
        rg!!.check(0)
        benar = 0
        salah = 0
    }

    fun next(view: View?) =
        if (PilihanA!!.isChecked || PilihanB!!.isChecked || PilihanC!!.isChecked || PilihanD!!.isChecked) {
            val jawaban_user = findViewById<View>(rg!!.checkedRadioButtonId) as RadioButton
            val ambil_jawaban_user = jawaban_user.text.toString()
            rg!!.check(0)
            if (ambil_jawaban_user.equals(
                    jawaban_benar[nomor],
                    ignoreCase = true
                )
            ) benar++ else salah++
            nomor++
            if (nomor < pertanyaan_kuis.size) {
                kuis!!.text = pertanyaan_kuis[nomor]
                PilihanA!!.text = pilihan_jawaban[nomor * 4 + 0]
                PilihanB!!.text = pilihan_jawaban[nomor * 4 + 1]
                PilihanC!!.text = pilihan_jawaban[nomor * 4 + 2]
                PilihanD!!.text = pilihan_jawaban[nomor * 4 + 3]
            } else {
                hasil = benar * 20
                val selesai = Intent(applicationContext, HasilKuis::class.java)
                startActivity(selesai)

            }
        } else {
            Toast.makeText(this, "Pilih Salah Satu ", Toast.LENGTH_LONG).show()
        }

    companion object {
        var hasil = 0
        var benar = 0
        var salah = 0
    }
}
