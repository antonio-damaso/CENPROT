package teste;

import br.com.cartorio.protesto.cenprot.Cenprot;
import br.com.cartorio.protesto.cenprot.CenprotAnuencia;
import br.com.cartorio.protesto.cenprot.CenprotApresentante;
import br.com.cartorio.protesto.cenprot.CenprotCancelamentoOnline;
import br.com.cartorio.protesto.cenprot.CenprotDevedor;
import br.com.cartorio.protesto.cenprot.CenprotEndereco;
import br.com.cartorio.protesto.cenprot.CenprotParte;
import br.com.cartorio.protesto.cenprot.CenprotTitulo;
import br.com.cartorio.protesto.cenprot.arquivo.CenprotFile;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

public class CenprotSaveTest 
{
    private final String INSTRUMENTO  = "UEsDBBQAAAAIAAh7ZE7d+CjKZSYAAHc0AAANABwATDA5NzdGMDgxLnBkZlVUCQADT219XE9tfVx1eAsAAQT2AQAABAYAAAC1m2mXskqW79/zKVIUZ0YZBMWRQXEARRwZRVREZVB79e0X97Nfz1N16tSq03P3dWWuhZsg4h+xg4j9i9wJaYIE4wgJ4D/YT3y8Ae02gA6zwHvH2U9ZzTz/HuDYZUn89PWfZZDE2fv1owfZP4V+8Kr8tWgYPwXvHfyUBY7AcBZrYCROEd/fb4FZfPo372lZfPr4wd8a+vmK+Tll4T8F2ffuKnx/TeXvVf/zvv4h53cZFaDTAYLn6TfV1N/Uo9PgeXlff+ivZQmgUnh/fxs4oH19OB43KSHw41Pwg0r3r6i/fLF+q+f1/nbkAcjXVUj7eWLpFM3T4nDvf7CaQqr3obfF8ojYja8VRb9VZJJDapEyeV3H1fL+bhqJ5oOr98pYdUTxOe7ZuA0D1dNt+GjSpeidashhTp2GI5jelXlbimCa45bBc+lA6IHaqcZp+MynjWEe2+jCPS8/n8SNjz76Nd840UefeQMmtLxKT5rJNxY9A+5u5SGyDYOQCdDh81Tb7lv82BRV3nN39SprcXyOR7OJFeAs60bWuBav1MI06kKY95IB6yUcvU5j96J2w8HlWdgYOF6/4Hw2IDKakS5uJMpsxx8fN1fv+5Mo1MwJ8W8VmAQ+4GTAklHLHO0PbBUFzulYHu3d1uT+aAra89HIoDEYjHcZGHy0kjGdTogtbhQfG22cJu449TvokEJFP4Bzlfua7A7WJ7xcbdatiQkU3FmzeaU4fH70BZhoDJpx95A51V2jM+itQFJO3IBpDEZ2F9cQY2F31qen/8qoRXjp+a9efPCbO02H8bP+AES2Z6irdkeL6huUP8IBPaBQiud64M5ISZWaU2QPKuQPELJPtp0PcjCrztJoJOwKUS/1JE3kXSQNmPXunAfMmHcymfFNs8LCKygDCW/yqHAr/6A1z51aNetXmxxMyHZuHA4e+LV3vXYF8OF1S6+ZMetOKvyL0nrFJFA6QH2+NY+jRo8s7EXLwGoiVlxJysQo7TSQtxevefPIt7hlY1GAwlHc68+X8+6h66cksfSTumo9y2Ln/ipsp1EGaK5ZH0Y87qVVrqu+pDuzfw3jMRWukM9gVZTUbsVpUCO/XW/UXh1dtNduvtXbqLumN+vd7h2w1kofbEkhzS2wfq/9W1Sf7YvY/rIN+ylnIH304ZT6eF3e7cfs0M292yOZM43p4Nhv33tC6eikUIWat+pnLza0+5HFjzW7sgFOjlGNVjs8ly1bAknvlbOeiP0dtxJbh8kJbnodvXOc5pPm+eC+GsJwl8zgD+6Mp3f2vbtZHeZKnDtPKo7OVUC8FV8JR9M3+dE9eG8+GjQxzTpcJ6r6Ph/45qzMQJWaishrbt7fd22uT6u8YbXkfIcaMGNndAxHC21kQSkFnDFa6PReeLk7a++mcPrYGYJnhOZ8mvFT+7wiposXam2q7eZs4eyTAEQJYwJu6zC2TRFw5Yr8ohv0UXYeTYDBKFYWYBnvSmrYb2GLZnPj9LmHpgUO90AU0vL0oKO5CxG8n6olUtFkyigc07QiQi3Z5/Q5X7Ekq/yefVdeUeM/wchCasYR+6y50bP6EqQHCz729b5cvt2ZQQn7jCJEoLpPaerhk1y/FO2LC5xv9xNwja9rJ3PSZLnWaArgxNGLUWXfjYkeVlw2nOnbhZi336buWEP1+tdmx4vV56hU/IwVc7O3uN2ohYrmRLmn4nnt3IqIcFh6UD+KgOCWb0Fa+nkSrelSPFZP2nh6BqvjaaxuZ8mz2XDDrt1prNvXqcZ7UqVHMFtHyIrDoEhTRVLuqifMarDUzfKB0q5nx8x2aKbXxYV6w/VK+rDRbRRmc3UIX2RX4a7WuCPPDl7tQU4GjD0kldeg7/qr3n4dP8xDMbXbl2s5pIEESUBr6XWwZKpwLMOb6moWUZ+1gMk6KBT93HHPfRxwzyYMVV9uG/qYyGBcrsguMTqxRbDTSB8xlU8a/BbIj5XHQr+dWuz1MR8+cHV9Uha0TnLlA3eG56V5l0PX+cVupKxPosXrqlGsomptf9twxh0davnZm1KIaUfe94Hz8fUaPVQDocvopXZLLTeRWrp0JGtJ9saQUZrLxPDstavXYqNNbhScPb6W/GS4cI7mLZjbUAtlwIZQOO9awLy7w+pa/n6q9y6MxXd2r4khPIZj5FbMNLs0TqpMVO/cBu37ZLKA/N7CqZ1dZVeY+BVfr6KOdFvYR2WFx9oO2JFRP11Pn2KhTrQ98VmaK9dux6xAWe04UeB+IWiryHGQbp6FTN2stEEGPeso1jjWpLo0TqNl+7OEV9MSXbwC3dLi865MjaPnOCwJdjtSt+PAr9OrOMD30yCXvfmUnF1rp1KtgbZrmFu87XCoN4S90m6s4gH4tA5ZmTX66RjoHTTXovsn+lZT41bTYxX3YF6QkHuuzVYRw8T7fcXjriJeJX1CQQi8wVRfedjSYJ/V9VFabUkpkbUOeXIIKDD41sgLw5miVWpJJfzMhudHfhzDeb/Y6aKuFaGmZCIwt01km1GKujwuXzh9x5tTso+00mSWW3fq397JgPuxWz3UgqR2qyyVp06p6p7wQTmF45Mc4o0Tsqi4z1zrc8EapFDDqqW1ji3VU5p6V4SZ3+3BFW+QlSJ+W7aBqVI1u93Pk58fQ3XQqfZm9t2ZqCLd58qV7b2q2+QVFstw8kxX+VBCq/F5adZ6s9oLyR89T73J+1sVVEnBGAMKOzgWI2N8LMZG9iTss+LHTUjS+3mx0mmD9ipwMf189eNSp+VW5uO3MIe+HXlyTP9aNJbnt2wHlWLDRR4W8I2K5EZWhDOuzKi95EYjDb232mxPLdfdPndwgwNPF+eAYBMQKi3LuRYunSTN3ifJu73s9EVmvju2S5X3+2QCy0LRrOnlz0FpdOG7IObH2bSrjsp5+yQzpXy6zMM7tb5fmm0I7piFuFVir41dpA2nUWVZ2qRcblicBZ7D5FJAXRLluSRaoyfb7M3yFNbqzTAimakmyMNGMnPlEyG9bBVRZHNSS5DWAT+SaBj3CtiEQvQ25icJC3L7lLMB7XVqpuu0r43nB4Hsd3azuHJ5IdwRnMuvYgTPcZxsOrlbZ/rmMporBnwwqwUz5rsR7L2IfOQPe7VUPc3e1wHQx2FQmYef4LsBnxb66lWzj7ejuC1OV6IyJnHBzEEzlJqubFMuzBnufpi9cek7d70eiXsmuVxXvfwygvcrDACFuuv0RuNOvK6gBxWi9Y6Wa9pCupuy09IsVvrNR3j6HBqXfqM55vlKYCbh+LtFtUAfOmxb2unmtdmBwrAwYM7Gh+o1K96qw+d2E6qdZ25uj5S2d8CM7XvfDwrtK/iYsAZ3o1ePoWhUVtPx07QyT9zAs1KVF9eXRS6dGIQJSIh3+lz8niQ29OZC7VepF9H8ONvKsdaeCOZ7fR8wSLQr9/dU0TpuZXrweLebeGWeq9dMY+XuWIZ9ZG7POQZAVnuVbtL9uLbO+mHdrmC1o3Vy5/TCDnt6RzSwCVy0OFNbv7epzvc+XOtwCir71I1LzHRagHdVZj+8Hqxg5wI28bGrPWHDybvD7DGiZCfz5IEn5a+fd5jV0Xkf7OGxNU7W5SPnK3VfzpSB/La14YsscujcciR/WxPwR+kB5LuVVQ6GvPMb71srjjnoxXs2/lBav6FxZ/W6FEQNmnTpJd+ttc+PZySNryUVrFJ9ee8iuaInM7tg2tmdCQoYzlF3u4+1UElqXSWaPfcFiJ70y8qzSy3n//cXm/wVK/5KKfRfKaXxnXi/25g/yGX1f5LfyCR+vgF07j1+u8YAVP8c379u/HYbB1Dx+YWW8Hn5QTfhs/98hb8bAHTgvYLfHv9B8xSZp9k8LeRpKs808oSQJ4k8LeXpYZ4m/w6cmv9u8/j/XvPUX5r/WvA8Lf6dAvbfVUD8dxWQjd/aY77NE7+k/Lr4N4cBx/4Of/8iY6seb4H//rv2xw/vEvyuLHx8pWzC0xc1G9TXTaMgvFzfPwz1VRG+X1qQDeNHEj+Dr5rml5Pje5zpiecHXxYdP0/BPwenH1QIfuPopTz4ISjqB/+NwZfWfw5af4DfSRcnfqHu30HsEURrEM/fwNNHOq2nen0iV8BHivqF6qnpNl6D67VaGkBnej50LiDU4sS+Au6JdM83tT2ii8aty2V+TmvVgRfK+MZmdUG5MGAgZSGWICpHtcbriZXnrC29yte4vlJ+EsMnfAOtNd+4sK9PgEvDXSPkdh/hki6xdFoHqwNAzK/AA+JXy0u2tX1dkJvfxcdVuJ+bZN1jdTNvy3zrEFfEGfON59luwulf0tk2D5C5w3L744NNX2F/XPHskw+MiLTQiu4wUtOXqbcsxPXjOp8g8Kl06bTkTg4qWrh3jrzaqOETq+dmLvfN5sdcFA7s7nJtmovnYGdSJphAFWATD+b3wwY/aRDFbA66OpidtuvxDQpGa+oY6dXcpOnk27jpPJX8IVCw/vkZRDeB2XWMEcPl6edGkQdQaaz4QHf2UEZ+Shmp3/PUBhl5jVzyvh2Te6JXUJFntuSwdqjtykak7C+TEl8/RbeJVWYn2XzeZpQumxKDyPS7egOAtmf+PtkR55Hpk5yl6ag8nR57hji/2jgCWWOQtDaC9OxVaHJfl7ZrRI/J/ND4yNE4zYJ1tr9NkBtWznwBIOLNMAEvXdaFCsOxiKXzpcPEE5Q/Q6WsWy637Lb1mDoFPhnUKUuc7bmaW2fouT0PjYu5izWryIuVkkAiS6C22wrdC/cA7c6LNdvyvViVF/TsizSd6pfjPkIQjUj40CEfQs2QCL51+Q6XLeaNzF2CcolsgWD7sDm8nCINdKBzw2s99u/GatPnvXYnV8iD/nKWvy4bck9ZdBvvnJKLmit2uZEMD0yr0qlDCkwG9t85oSmKPPcNxQry0VcA6q4ikdi8CCF52qzS/GbRLpzGBa1KUEWl1F2hzLqlGveRZ61gtXOJDKkB0iMEvxSP2pUfLtenvrQzms5pDwPr5QrB4Py+oDtob9I5lq19NSxsHzwDjgp6s5ivbsAym9iwMTdVt3bwAgQjFs3iJy82m49VKQnm9c0gJnjlu8xg0aDQCsvdN0Nc/NILaW0Cu/kJgseuRlJYWuv2tOnN6o3e2ms7fMFDk3guC5czvSeYyr34th8zFDSL96M4A8I+fdOp7uj51Lng6pbZA/vJM+J4bPhCD3XGuymV0jmx3tiWI+qEGc/rAZlWBlskI+atXq0gp/igNNF843oDLvAEe9/2nCYnDWlYnkrQZqTlNkycb+dge+AeD3vhNp1NQHJbSef1QVQo7R6ngT9rj8qHQ6shX4zOANrjklkD4O+cZ66T69A8nPBKifMNSX5UhFgXetLjLYrx+EzArrJpOwZmEIPUV1TiMPms0gtywazNseNXKcRYPXe0BBTi/X4B9QrxI0Tq+UO7icGHJdP2OtUZqyT9tVuGgsWrU261K0bZ8bL7bJQQGEOHk+AF7j90nxks9a632Q944Fx1HPxsfdlirvN3K7dZVvzA0jEut+IbMVTNTcetC/qeONR18zHAObdYEVo5tmOLzwvuEqt5rWzy7IqedgfkcqFRNye5Q700378ybHTdfjaQ3lU8xd2UmpmX7sdw9Iil8lstd/OXZsFsTXdt5eUizmqibr50QjPtJoft7sBmOFh374VBAe2Clcb5qMt+43GmymFIw+qCdgQeaa3UqNZIKwuc3UsD4u46nS0vbOCCWStxNZnKdLdqEIoHtBaWxTXuM/ycItxsN7s/+salM9FakQeBK84+UAthel/VH2H//URWUKvZxVeKDNf4p1jagObuNG7q6PDrtBqwPw5UTKa3nVIB9bIcSyLh+e5ZyVTLQL1CwQo72bLCA7Z8aAom9JqobN0d1jfdPjkzS+2zNRpOC5paLCh3YHhuxs9rtN96G70i2aX35oJd/Qhnxzbq1QQlyJrrVaP82GeN2WVRR+VN7bW+6oGWp7iGIt59GcQGlgJPyAZQHETrW50G10it391F7dZS8XclSBcV/OLXCUy3YWlRWMScVfdk6rXlUgu7X2v91FFP/udC2TtTC8p2X69fAeZdui0mp66/HfexvpP/8Hq+aLaZ4RXvyTUf+zyPd8xpXE7VvvwZzsLG3Bq/7d1EnvW5dN7cqos5qw3mNHo0gdWSDNTTAXa68uE224EdJLeaaNiDNM/wyGsIkSD1xpZicVKoUW6feG2pS+UzK1Ym9iAt5I3xNKyzpaT/XFSAWTSo35MxLq3jwTnzOkWwfRVL99ygq2RkWy+Hdz3tQbo1vJAgJtH55iTkvD12fU4G4Z6OGpNFLFTA3vUhXIDNhTQNPjjb8we47k6/obtqT/zWoIKo9pm9jvRt6nrZHG47pnjqzsozEso1t800KOvgjhBRr3oa0HW1/WgFQOQVs52HmuFC8VGldCPcPVu+70dNydoMJxBmRfYVszTUOK13TfNLpp8i0SjOqqPvpCvWHilCe8qCre5aSgw88oG4yfkw8mKuXfK7aPp8c7N4aejJHMPYWBpY/lyAVy3eN9W4ZdVHpUN/XH52eS7fPZioSlTcy0dPhdrSBRI8zeUqcSepeMNH8lLK+c12De7GksTadUskWW4XQSPzUdmbgwr2GGqL4nIKhUe2cXDghVPWsx6/HRvT1fMDFPDwBYr73UabzohmLz9VAu3agT71qTBoSnU8zq2WK/Zotpb5+3xeJKhhpL9eKe5Ix40Czx0/spNtaeU1pzyAygNrZq0/wnS/GSdR7+FfyfJsXwDLw0ZtyT2s5smDm1mpXH6cg3yPNYPNi0hWVirC4rU1wWYPp1D1sPhNV4HC2vKNtVUG2WOq1bRpZtvs6vUa8SqzOtBOSyTt0JNAvTe3yAJ6/OyUBeHk7ZAXvJOF5W39eC6MonLmjCmAr5wR6Hp5iyd3uE4EyB4GvHNBbKn9ym9Tr75y+QnOFp76xK19I8tLAJ2NQ5A9oWtQ2aXuKj896pbatrz7Hih0k7hVFqXDg566NaZ5Lhhe8VFbjsCTA+LoK84gs1rCpjt6sDj1z5lbP9UvwVrU1xtqkDOdd3mNmdKxff0cgeiWkSVetQpJA7uRl9zILeKwb8DyRN7Wm6POzSmixydmyNOdhO17idsUmunjQaOwD3KMbjidtLJdUGXnTAKPyaHWUZc9HpKZ0BHAO61j2P219YR8r752NXPNg9eWquFRUMjfet3kfrTWlZrUNvF6jYIOSbKdVPHntGCXACN5GvXz6iApTa93pLpDKeh0ZA7Jg9LaxiWUCTpn5m35LLK0sfu6PZuO86/G07VrZg2/VLeyO6Ox3GFZPewBXkMq1Fm54ysyw9ZmwUFw5s7uvxuZw3Cx3S3s5t1OSRXzp8DVhrCsOB7hBWaKy9N7ShzuZK27Hc2+dB4iQLGpZ0GVOvEfL37XyGPtg5SIDXvqR5dTAS4PreQi70uVc8THXYLJAnJDpJnPHZYeS4MfyCntRbXbfZZWIgysJmLuux5TR20wYS7KctIFrcf83pvKbiyRu/JB8Zt9wj+N3sZcPJe4qkihAXVQwuWZRYZYtJ8z19I8P7lmDSBg64Pb/QBV1FXRh+BHDQrGcbVevh77UZvwP66KyoviN1rHD1f3MPWqt7ta9B6z55TQr2qp0nqQDc5Vcm/jDDyq7PCUFdPN2e0SlaWTDT/rMVJtPJtILUC3Uc5JSIeib1KlkZjZdk4licyg9djoYWEAkjQdyEb9WYb2YRsoLc614HrL3Wyl+5lgyaTFBVInLpyebFUg/Mfi3eAoZ5DYX/fqC3XEL3ett/CONoy711QV+25s80oVyp3gDFDj0S2ikOZeuvjDA85afroLZAJXIKKg11xblj8hwewJfwttN9kWGc+R8XE80dz2l1osZRL5k06yB/3XcQ6MdirS7qSfcNtyICU0n0h9tOfD41QSTbxP194FSO7nc9vYQaC0HKTC4qZuNuLhcnPUyh7uGNdI/UDTwn13AeIMOZakwak5zfWWka/bds9R8dEdYwul6tYQyPph3EGpW+618tHqMphDLE+uduh9v6hhu1llcApppR4FU3YKpBd66si58QzBx0VsxZ+bi6rL7Ok3fMybFzeemVkrh+bO3SK0b1ykUjYOSJYm1aeO3I3CiZ/ZhfsrWant4hqoj8tJvnvvq+96lFHhxU516RC2+8mma3owyg8lmVPHj5wZzpTTrjkTpESaTsHOYFeKW2AJP0BXlFmMhspO/8a+VeWDn/Ry9yuk9pDuFaL8DaUb10QY6vfwbTQHooht2rRX+AyepcsRs3L6A465oxvJ5CQIJxtvJtx3o6sNPIvF0+E5ZybvPjpr7sa+PXOEc9efuLDFYEdTqRVwj64YKLG/6/FjjbpOA4S3SZVdMdXSwkjTuQzDS0haSQB1RO2ZUALjDsgaINTNoGZdPp4mE+l82F2HvVLO6fiSW2p+wzm/7lrtxmp9rpJRIZ1stK1aP1RXuaNjbHEKBRS3loPqY50zb6Ohh/bG+PpNuNEc4+HjcB8h9Yl7tIVLVEP5g1vbbSbWqrLZK6L5LgzMIzHOcTsdxKXCszMEtp/ixpbOw1wuMfraBZfR3ulUI5C3ujbf1dJr0LyBhQSatcja0dpHG7RnURfu8yWL8kaAZjynavNeq6Yt6QQoFaz5a/IOMGvCdenmcM1Yzok1XgeTXQcnv7ftZ9GVvh85Mizur5PJOrcjbeUjIKN0CLvgJFxTZ0mLtlX6DmAlAulWX22J2VLlepBLr0GSr0qbkm2b+VKxNBXnHZ1EBdPeT7+E+1b63/m/xoUTb1T9lBHPY4j+DON2YiqAPEOdMpTcpFn4DMBVdWzUOPeYf/c2qi8nxWUohI8UxeBru++8o/vgUxvLEo10Xpu+eeBd/crMt7Po0JulJOB0VpsPDWv196T3FBGWDXVnu6tF2X7Omt7mkmYtNYBGUAJVU6jt+YNqin7Li3PEmQjOyn9+SR2KbaT/GAINZT9Ck8tJdg45hkPLo7QKVh+eaK8HlwJVRkVxt6YvJKs/Ca9gqpEVViTIcCm6S37uum98xpWu+d2KvMgG7vXp9NYfcA8phJ+QPaoJD8W9s+sQ7oMxicKD9BuTHeXwapX0tHbY3u/2yRhdCGzN9Det1pnrdI7ZGBG2qARwzGB/oO9Ud9uSTHo5lsAuqK0oSd2m8trXb9m40FrgqtFSBgzVT7jo7VZaAlcri2PHEk+QVNj6RycHMtMhsOKoXfUbqXXWg6VYyWqPWYD0omd6rUdpMMHunEXfS/cV6HvbcJUM349dYsXUO7iuB6+Sl5GidnBC5/Auj+dAbb+e9r8LykdkBltj0Yh6Wid4RkIwpKn9BcxJtiRuLePizkSnuFU/81zLQrYFeRhbt2G+/Cw/zUpXG/WQ/API0Tidn4Z7rAF2OGIslnKvxdUswEzVO8YHYhSLGoWNSF0XuYG3qHY3rHSDUByhb3XjHEUubk77AlkyOrkeMEknvegMMT6Rx6tW4gu1h4d0P1WOTa3OiIV6+YdAuXWsM0zkRj7u9oysOwLjCyl9avn5xRrv80uOnpVWWhW4hS6iZqWtC5fVrlcbxE/KWtTb1jwsvWg32x/bPe3d6ZeWwrWTL/AVxW/89ldCilsdbGLDi1j9XE6D4lutl79zVadHFD/xpqi/i9R/7bT517ngr+QYHGP/Zmz81w86id8POnGW+dtBZ/N/ctBJ/vcOOqk/HXTm8s75CZPbwr3aK/a627qU22ywxY49iJASNdoUuRs9eIFjL/ems46Owwy05Y6RsHaxU/xKFjdZbtpOvi/G+FG8LMTS0ZXtKTQnS3hnBl7ffspeu8vB8xqKzEyLNO9di6fiovdSonxHaM0fG4aCtQrb9buhQMK3a0nq1IFojr4MvdltfQobunN/2gU+TM/GeMR7Rm145Dk7BxeZqD4/jouI1t08h9y8PDovDJtYXonCDjogqulvP4VyH1ja27Yibeg32ckW6AkiRog28Yvdd66+6h0Pb9zrV6VWm/KgDL/nyoTvWHZjf1ULVH0RjsVIwPIDux+tmuQWwCvXz/pRf0dtuPRdQcl2eTpVvwgDz/PnipkZ5gUqxE9iCifDzlsPL5Mgr8+d7pm/dB7rq6Zd8kWke89ldfa7WFabfmnSHzbsY7feIbgjMahhQQPk1PPhlmyHMmNvbqs4xBbdkd1rG+7lquzhVc1PZlmCFc5hby3PWFCeaGtALj0Fg5G1NOcgedfGwAM8b20VwlkffcaYvA9e/9aZ9JrQkLvfL3s+Ok5DVGyTEFOrwVClmp0Qddhn7x3EA4rwPV1BTiF70p0Waqqk4G/ARj/Sqb47igcytodrROwYuXW8bAtjsViWd/OPPEN2fAVfldUG83waxeLVsnPAQpnvuur7mVvUjm5DlaaouwZlS6Xs95qg6Y8hPygn7OV4JL0lLhbj3RPdlh7Smcwej8YRqd2coyMbTtJmgLWU+9RyY6PNY+Fts/RekJYymzT/Whvvds7BGvySzNKEE7iwNcp8/NKvRVdKLTbeCC/yTBVB3XBv9Bt+rwWsDp5RlZkFqlwSe/0AdR7S68vBfPHM7fkdg/pEqkNcJFXWQWxt6+1tW1u7p3UruujJdPUovyq77vmT5cc8A9hgEPYajCOjwagWHdadiy/wU/PVqVs2SK9alYIZDjLixh+HuOq8xsscI7NViswi7WbWK6Nti5Qmr+TmYGvgS2NH4dale++Tkg5julQyd+XVXLxtz8i85UF0SqSyV5Nrp2bjsF7MYa00W94NsigqyvnMFpLNNp31RLYnJEBf7gny1ewLj2KJnZQLfSh89iH6O6oKhFzjQ3k0lyXOMesxt/Fv1eJWMTrTPUFUaWIWqu1B49mm1glVERYwQEe3O+yrarOaBHlfxw6vbETU2vnud9GddA79Q2e+8IuT1+h5dpLIJopdXcl3tStEZ+7mbVyfLfw0xxbP/aYLwHFTGwXssnOfPqHt2TFaxSRq5caFpKrKNBK6GqvflpazhLONoF0+8evFFMbLVpW6ES5S1LXy5qw04Y/QN4DJ8dTrDRwU/WwetgzCw6QnkNJaGO5HH3LKeRWyVRhbEaM2P/6IxrogeTu6JOMdC+zHEB/2O+vBd33O34bD72qftbbz5jpsHm+Dcr85mq87Uy4Oz9u4Z96pm7d8xOGU1dvy3B9Fdi34vt2qfBgyLPd24UcV3d/G533zHBVY4IUv7vL6gLnOOriVVn3PugXkZ1RWdl3oKpiOsXjPkVx+CVko+SxRwwo/6Y1OrnTC19KxF+Z46xKRM62NoxFAHtxtMSPILf/cdl87bpUS9coEzReJB6biicjUa/4ELcVQjBa6UmH63KwryWvGPhGi7daOSgf1wuFwX1xoGnDrzt6VYTgUO/dr19yYGW75m2UhcddZL9qbfH0VnOlXvDj11NvK0e/bJ1UpFbO+/UXKIdhiAzdIBovruWJDwP3zaXs0WjHwSu5S7aRkcIyCRpZRQacHQdddqPUqqPEpqqbx3ixHgpofNN2yUio9qZYD0renAC4OSJmHkBaQEQtcGL1OX7jZ3k5kZcaq22J3UfTZDrzS9FnXePjHibHKVv6QnFhhcMXbTMQON9oDxtvb9RtRZMKcTyh1Baxz7XXZlzunpvOF9pyxjLaHh7TTn1M8XSB38lN10Fvd5SsXamYMdsfHofm66Hr/hF7rubixIsu34E5jbQU3gSqLNsr6jhwczjcxyBWOg7fgrRZ7+759HbPyw3F4Th4fmPYi7PTHr9tS5GF5gBXC52xwvzNEaYaWtyWNIPsu4DXYVYZ5nQJkv2u0QIaJEmeT5iK9rDhh14+CAr3of2F3dyKWvUKJWDY6997NuZeqb9JNwJMtm4cZE7ZIfwbkZZF4YlmfWPPYp0WH0nVrVUjJneLVlxJj1315gKTz1iAq3tKcuC/dvW3ueDhe59X7pGHcPkvtuecDgajPbKAkjHihqlXrlHhrRoXQ8eLw4Z2hJY2L2XjN89UBZFd3l2YKmWlN+/B1onT5xivspjom9fLEaBHl3b4stZkG0G1uJBtUc5kyaQVoT8qNHjQ101lTcauqhL72/CwqjsxKv+3Momldq9dKU6T1WLvFp1CyvDNoeJdaQXdl3gUuR2G1tFwvL1VU43i6lvcIiGdWnTcWkdpkbmTdRK9I2ijnGY5euBO7UedT6KZd3+33VLQqXMmVHjeeKvsqMB4vBLmTmTB/RKsfbMvw3O0F1cvpiMkL+oVQN8VdHc6uY3rC6pf+xyUkWM+knWw3UyEZYki80kdf5rrNKKB7u+OKNN2jVZHKx6aBx7vTbKqvQu0drTFNVGcvRaJ5WNhX/AG0DW/1LV4R/PF9L/fbmUvm/9VQ8/f8a4IkGn8z/pFS/m8EetYfod2vdO0/IrtXc1O5xuArDZ5Df6I7Ta5UMW9u49KU7v3GbNZIB+dbe3y9JHz/uR6ptwptFHKz8WoDIbXB6uIPgkHfz9HYawz0mRV9ra/w96bgtEMjLtM5qXFtrcsnbpQ8XS1+8lVlayT/8h9+gP+4yH/28z+r6l/1we/ZJUTzj2if/C+4gPmTC+oNa1cY3MjDNUAOpUOjaei94Jqrhj6dQL0Pg/s4oc5Hk/6pEFXVfckH7bprVF5bdtaH0fH2kKtMTkx8BW53TTfQGVmtb0+f/LLa7aJHzSuDhytaeAqB7RLTduVaD3yw3E3/P47b/3JV/6obfk/oIdi/vQnkn5hL+wVYs+AUeoP4n38Ov6Wf0E3yh2GJr0c0L/uNphp/+ReGZfCKP5kfvH6+XtSy2NeD98/hW4cg/aCr4J/ffwW28Y/1l+SZb7nfUod+fjn0tyyen+Zfr4gf9tdVp/M37PtVOHzgP79SYJa/eO/nFyX+KvadD1+ke76/el4/f+EvdJjFyZ9V/5FMQ/ypu0Pv7d3jC/Cr36+/duyPB/7MpL/KAegkPL2+Xf01gMsf6zcpn2//8L979p+z4AxgP3gTwP72+aEpqkH9nH/+sH37/evO8682nGh+hf+jjW00/mSjSeofbF8r+4/1YQ2ywfzZRv3ZRv2pXazB4H+2Ncl/fBbHm/Sf6mNx5k9aWOL77R/7QVB/6i/eJOk/l2v8eVxo4u/G4J154T3IfnlLD/8l+G3s0WUcv3/+koj0pf5z/IP/wepe9v7lpe/4ft8JCBJV6f8BUEsBAh4DFAAAAAgACHtkTt34KMplJgAAdzQAAA0AGAAAAAAAAQAAAKSBAAAAAEwwOTc3RjA4MS5wZGZVVAUAA09tfVx1eAsAAQT2AQAABAYAAABQSwUGAAAAAAEAAQBTAAAArCYAAAAA";
    private final String XSD_FILENAME = "/home/avld/Cartorio/documentação/CENPROT/v1_19.xsd";
    
    @Test
    public void exemplo() throws Exception
    {
        Cenprot cenprot = new Cenprot();
        cenprot.setUF( "RJ" );
        cenprot.setCodigoIBGE( "3302700" );
        cenprot.setCodigoCartorio( "000001" );
        
        // ---------------- apenas para compor o nome do arquivo
        cenprot.setTipoTransacao( "P" );  
        cenprot.setDataEnvio( data( 18 , 1 , 2019 ) );
        cenprot.setRemessaNumero ( 1 );
        
        cenprot.getApresentantes().add( new CenprotApresentante()
            .setNome  ( "BANCO BRADESCO S/A" )
            .setCpfOuCnpj( "60746948242043" )
            .setCodigo( "237" )
            .setTipo  ( CenprotApresentante.TIPO_BANCO )
            .add( new CenprotEndereco()
                .setEndereco( "RUA RIBEIRO DE ALMEIDA,077" )
                .setComplemento( "CASA 01" )
                .setCep( "24900001" )
                .setBairro( "CENTRO" )
                .setCidade( "MARICA" )
                .setUf( "RJ" )
            )
            .add( new CenprotTitulo()
                .setChaveUnica( "33027000134943001190326" )
                .setCedente( new CenprotParte()
                    .setNome( "LORENZETTI SA" )
                    .setCpfOuCnpj( "61413282000143" ) 
                    .add( new CenprotEndereco()
                        .setEndereco( "RUA DO NORTE 153" )
                        .setComplemento( "SALA 01" )
                        .setCep( "21110000" )
                        .setBairro( "CENTRO" )
                        .setCidade( "RIO DE JANEIRO" )
                        .setUf( "RJ" )
                    )
                )
                .setSacador( new CenprotParte()
                    .setNome( "LORENZETTI SA" )
                    .setCpfOuCnpj( "61413282000143" ) 
                    .add( new CenprotEndereco()
                        .setEndereco( "AV PRESIDENTE WILSON 1230" )
                        .setComplemento( "SALA 01" )
                        .setCep( "03107901" )
                        .setBairro( "CENTRO" )
                        .setCidade( "SAO PAULO" )
                        .setUf( "SP" )
                    )
                )
                .add( new CenprotDevedor()
                    .setNome( "SA REGO E TEIXEIRA LTDA" ) 
                    .setCpfOuCnpj( "29789518000138" ) 
                    .setIntimado( true )
                    .setEdital( true )
                    .setEditalMotivo( "AR NAO RETORNOU" )
                    .addEndereco( new CenprotEndereco()
                        .setEndereco( "RUA RIBEIRO DE ALMEIDA,077" )
                        .setComplemento( "SALA 01" )
                        .setBairro( "CENTRO" )
                        .setCidade( "MARICA" )
                        .setUf( "RJ" )
                        .setCep( "24900001" )
                    ) 
                )
                    
                .setNossoNumero( "1426919/01" )
                .setEspecie( "DMI" )
                .setNumeroTitulo( "1426919/01" )
                .setDataEmissao( data( 30 , 11 , 2018 ) )
                .setDataVencimento( "2019-01-18" )
                .setTipoMoeda( "1" )
                .setValorTitulo( 1764.55 )
                .setValorProtestado( 1764.55 )
                .setPracaProtesto( "MARICA" )
                .setEndosso( "G" )
                .setTipoEndosso( "M" )
                .setAceite( "N" )

                .setDataApresentacao( data( 24 , 1 , 2019 ) )
                .setProtocolo( "134943" )
                .setDataProtocolo( data( 24 , 1 , 2019 ) )
                .setOcorrencia( 2 )
                .setDataOcorrencia( data( 7 , 2 , 2019 ) )
                .setContratoOperacao( "12345" )
                .setContratoIdentificador( "237"   )
                .setContratoParcela ( "01" )
                .setContratoTipo( "C" )
                .setTipoProtesto( "C" )
                .setMotivoProtesto( 1 )
                .setValorOutrasDespesas( 10.00 )
                .setTipoLivro( "A" )
                .setNumeroLivro( 1 )
                .setNumeroFolha( 10 )
                .setAcervoHerdado( true )
                .setAcervoCartorio( 2 )
                .setDataAverbacao( data( 25 , 1, 2019 ) )
                .setFaixaValorProtestado( "C" )
                .add( new CenprotCancelamentoOnline( "04" , 1 ) )
                    .setAnuencia( new CenprotAnuencia()
                    .setFlag( true )
                    .setOrigem( 1 )
                    .setData( data( 28, 2 , 2019 ) )
                    .setApresentantePermitido( true ) 
                    .setSacadorPermitido( true  )
                    .setCedentePermitido( false ) 
                )
                .setMicroempresa( false )
                .setInstrumentoEletronico( INSTRUMENTO )
        ) );
        
        // -----
        
        validarComXSD( CenprotFile.salvar( new File( "/tmp/" ) , cenprot ) );
    }
    
    @Test
    public void nome()
    {
        Cenprot cenprot = new Cenprot();
        cenprot.setUF( "SP" );
        cenprot.setCodigoIBGE( "3550308" );
        cenprot.setTipoTransacao( "P" );
        cenprot.setDataEnvio( data( 18 , 1 , 2019 ) );
        cenprot.setRemessaNumero ( 1 );
        cenprot.setCodigoCartorio( "000001" );
        
        // -----
        
        // EE-MMMMMMM-CC-T-AAAAAMMDD-SS.xml
        String esperado  = "SP-3550308-01-P-20190118-01.xml";
        String resultado = CenprotFile.createName( cenprot );
        
        // -----
        
        Assert.assertEquals( esperado , resultado );
    }
    
    private Date data( int dia , int mes , int ano )
    {
        Calendar c = Calendar.getInstance();
        c.set( Calendar.DAY_OF_MONTH , dia );
        c.set( Calendar.MONTH        , mes - 1 );
        c.set( Calendar.YEAR         , ano );
        
        return c.getTime();
    }
    
    private void validarComXSD( File xmlFile )
    {
        System.out.println( "validando arquivo: " + xmlFile );
        
        SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
        
        Source xmlSource = new StreamSource( xmlFile );
        
        try
        {
            Schema schema = schemaFactory.newSchema( new File( XSD_FILENAME ) );
            Validator validator = schema.newValidator();
            validator.validate( xmlSource );
            
            System.out.println( xmlSource.getSystemId() + " é válido." );
        }
        catch( SAXException e )
        {
            Assert.assertTrue( "O XML gerado não é válido, porque: " + e , false );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
    
}
